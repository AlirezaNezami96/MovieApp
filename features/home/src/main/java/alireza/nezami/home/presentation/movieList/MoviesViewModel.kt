package alireza.nezami.home.presentation.movieList

import alireza.nezami.common.base.BaseViewModel
import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.domain.usecase.GetPopularMoviesUseCase
import alireza.nezami.home.presentation.movieList.contract.MoviesEvent
import alireza.nezami.home.presentation.movieList.contract.MoviesIntent
import alireza.nezami.home.presentation.movieList.contract.MoviesUiState
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    moviesUiState: MoviesUiState,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel<MoviesUiState, MoviesUiState.PartialState, MoviesEvent, MoviesIntent>(
    savedStateHandle,
    moviesUiState
) {

    init {
        acceptIntent(MoviesIntent.GetPopular(1))
    }

    override fun mapIntents(intent: MoviesIntent): Flow<MoviesUiState.PartialState> =
        when (intent) {
            is MoviesIntent.ChangeTab -> flowOf(MoviesUiState.PartialState.ChangeTab(intent.selectedTabIndex))
            is MoviesIntent.GetNowPlaying -> TODO()
            is MoviesIntent.GetPopular -> getPopularMovies(intent.page)
            is MoviesIntent.GetTopRated -> TODO()
            is MoviesIntent.GetUpcoming -> TODO()
            is MoviesIntent.OnMovieClick -> TODO()
            MoviesIntent.OnSearchClick -> TODO()
        }

    override fun reduceUiState(
        previousState: MoviesUiState,
        partialState: MoviesUiState.PartialState
    ): MoviesUiState =
        when (partialState) {
            is MoviesUiState.PartialState.AddNowPlayingMovies -> TODO()
            is MoviesUiState.PartialState.AddPopularMovies -> previousState.copy(
                isLoading = false,
                isError = false,
                popularMovies = partialState.topRatedMovies
            )

            is MoviesUiState.PartialState.AddTopRatedMovies -> TODO()
            is MoviesUiState.PartialState.AddUpcomingMovies -> TODO()
            is MoviesUiState.PartialState.Loading -> previousState.copy(
                isLoading = partialState.show,
                isError = false
            )

            is MoviesUiState.PartialState.ShowErrorDialog -> previousState.copy(
                isLoading = false,
                isError = true,
                errorMessage = partialState.message
            )

            is MoviesUiState.PartialState.ChangeTab -> previousState.copy(
                selectedTabIndex = partialState.selectedTabIndex
            )
        }


    private fun getPopularMovies(page: Int?): Flow<MoviesUiState.PartialState> = flow {
        getPopularMoviesUseCase(page)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(MoviesUiState.PartialState.ShowErrorDialog(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(MoviesUiState.PartialState.Loading(true))
                    is Result.Success -> emit(MoviesUiState.PartialState.AddPopularMovies(it.data))
                }
            }
            .catch {
                emit(MoviesUiState.PartialState.ShowErrorDialog(it.message.orEmpty()))
            }
            .collect()
    }


}