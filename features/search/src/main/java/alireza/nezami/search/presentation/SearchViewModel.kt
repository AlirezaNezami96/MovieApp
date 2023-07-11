package alireza.nezami.search.presentation

import alireza.nezami.common.base.BaseViewModel
import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.common.utils.flattenMerge
import alireza.nezami.domain.usecase.SearchForMoviesUseCase
import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
import alireza.nezami.search.presentation.contract.SearchEvent
import alireza.nezami.search.presentation.contract.SearchIntent
import alireza.nezami.search.presentation.contract.SearchUiState
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    SearchUiState: SearchUiState,
    private val searchForMoviesUseCase: SearchForMoviesUseCase
) : BaseViewModel<SearchUiState, SearchUiState.PartialState, SearchEvent, SearchIntent>(
    savedStateHandle,
    SearchUiState
) {

    override fun mapIntents(intent: SearchIntent): Flow<SearchUiState.PartialState> =
        when (intent) {
            is SearchIntent.OnMovieClick -> {
                publishEvent(SearchEvent.NavigateToMovieDetail(intent.id))
                emptyFlow()
            }
            is SearchIntent.EnterSearchQuery -> flattenMerge(
                flowOf(SearchUiState.PartialState.EnterSearchQuery(intent.query)),
                searchMovies(
                    page = 1,
                    query = MutableStateFlow(intent.query)
                )
            )

            SearchIntent.Paginate -> {
                with(uiState.value) {
                    if (searchResultList.isNotEmpty()) {
                        searchMovies(
                            page = searchResultState.nextPage,
                            query = MutableStateFlow(searchQuery)
                        )
                    } else emptyFlow()
                }
            }
        }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.PartialState
    ): SearchUiState =
        when (partialState) {
            is SearchUiState.PartialState.AddSearchResult -> previousState.copy(
                searchResultState = previousState.searchResultState.copy(
                    page = partialState.movies.page,
                    state = if (partialState.movies.results.isEmpty()) ListState.EMPTY
                    else ListState.IDLE
                ),
                searchResultList =
                if (partialState.movies.page == 1) partialState.movies.results
                else addMoviesToList(
                    previousState.searchResultList,
                    partialState.movies.results
                )
            )

            is SearchUiState.PartialState.SearchResultLoading -> previousState.copy(
                searchResultState = previousState.searchResultState.copy(
                    state = if (previousState.searchResultList.isEmpty()) ListState.LOADING
                    else ListState.PAGINATING
                )
            )

            is SearchUiState.PartialState.SearchResultError -> previousState.copy(
                searchResultState = previousState.searchResultState.copy(
                    state = ListState.ERROR,
                    errorMessage = partialState.message
                )
            )

            is SearchUiState.PartialState.EnterSearchQuery -> previousState.copy(
                searchQuery = partialState.query,
                searchResultState = previousState.searchResultState.copy(
                    page = 1
                ),
                searchResultList = if (partialState.query.isEmpty()) emptyList()
                else previousState.searchResultList
            )
        }


    private fun addMoviesToList(previousList: List<Movie>, newList: List<Movie>): List<Movie> {
        val finalList = previousList.toMutableList().apply {
            addAll(newList)
        }
        return finalList
    }


    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun searchMovies(
        page: Int,
        query: StateFlow<String>
    ): Flow<SearchUiState.PartialState> =
        query
            .debounce(500)
            .distinctUntilChanged()
            .filter { newQuery -> newQuery.isNotBlank() }
            .flatMapLatest { newQuery ->
                flow {
                    searchForMoviesUseCase(page = page, query = newQuery)
                        .asResult()
                        .map {
                            when (it) {
                                is Result.Error -> emit(
                                    SearchUiState.PartialState.SearchResultError(
                                        it.exception?.message.orEmpty()
                                    )
                                )

                                Result.Loading -> emit(
                                    SearchUiState.PartialState.SearchResultLoading(
                                        true
                                    )
                                )

                                is Result.Success -> emit(
                                    SearchUiState.PartialState.AddSearchResult(
                                        it.data
                                    )
                                )
                            }
                        }
                        .catch {
                            emit(SearchUiState.PartialState.SearchResultError(it.message.orEmpty()))
                        }
                        .collect()
                }
            }

}