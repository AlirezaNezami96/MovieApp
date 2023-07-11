package alireza.nezami.detail.presentation

import alireza.nezami.common.base.BaseViewModel
import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.detail.navigation.DetailArgs
import alireza.nezami.detail.presentation.contract.DetailEvent
import alireza.nezami.detail.presentation.contract.DetailIntent
import alireza.nezami.detail.presentation.contract.DetailUiState
import alireza.nezami.domain.usecase.GetMovieDetailUseCase
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
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    SearchUiState: DetailUiState,
    private val movieDetailUseCase: GetMovieDetailUseCase
) : BaseViewModel<DetailUiState, DetailUiState.PartialState, DetailEvent, DetailIntent>(
    savedStateHandle,
    SearchUiState
) {

    private val detailArgs: DetailArgs = DetailArgs(savedStateHandle)

    init {
        acceptIntent(DetailIntent.GetMovieDetail(detailArgs.movieId.toInt()))
    }

    override fun mapIntents(intent: DetailIntent): Flow<DetailUiState.PartialState> =
        when (intent) {
            is DetailIntent.ChangeTab -> flowOf(DetailUiState.PartialState.ChangeTab(intent.selectedTabIndex))
            is DetailIntent.GetMovieDetail -> getMovieDetail(intent.id)
            is DetailIntent.OnFavoriteClick -> TODO()
            DetailIntent.OnNavigateBackClick -> TODO()
        }

    override fun reduceUiState(
        previousState: DetailUiState,
        partialState: DetailUiState.PartialState
    ): DetailUiState =
        when (partialState) {
            is DetailUiState.PartialState.AddMovieDetail -> previousState.copy(
                movieDetail = partialState.detail,
                isLoading = false,
                isError = false
            )

            is DetailUiState.PartialState.Error -> previousState.copy(
                isError = true,
                isLoading = false
            )

            is DetailUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false
            )

            is DetailUiState.PartialState.MakeFavorite -> TODO()
            is DetailUiState.PartialState.ChangeTab -> previousState.copy(
                selectedTabIndex = partialState.selectedTabIndex
            )
        }


    private fun getMovieDetail(id: Int): Flow<DetailUiState.PartialState> = flow {
        movieDetailUseCase(id = id)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(DetailUiState.PartialState.Error(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(DetailUiState.PartialState.Loading(true))
                    is Result.Success -> emit(DetailUiState.PartialState.AddMovieDetail(it.data))
                }
            }
            .catch {
                emit(DetailUiState.PartialState.Error(it.message.orEmpty()))
            }
            .collect()
    }

}