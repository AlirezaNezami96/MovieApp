package alireza.nezami.detail.presentation

import alireza.nezami.common.base.BaseViewModel
import alireza.nezami.common.extensions.orZero
import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.detail.navigation.DetailArgs
import alireza.nezami.detail.presentation.contract.DetailEvent
import alireza.nezami.detail.presentation.contract.DetailIntent
import alireza.nezami.detail.presentation.contract.DetailUiState
import alireza.nezami.domain.usecase.AddMovieToFavoriteUseCase
import alireza.nezami.domain.usecase.DeleteFavoriteMovieUseCase
import alireza.nezami.domain.usecase.GetMovieDetailUseCase
import alireza.nezami.domain.usecase.IsMovieFavoriteUseCase
import alireza.nezami.model.movie.Movie
import alireza.nezami.model.movieDetial.toMovie
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    SearchUiState: DetailUiState,
    private val movieDetailUseCase: GetMovieDetailUseCase,
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase,
    private val addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
) : BaseViewModel<DetailUiState, DetailUiState.PartialState, DetailEvent, DetailIntent>(
    savedStateHandle,
    SearchUiState
) {

    private val detailArgs: DetailArgs = DetailArgs(savedStateHandle)

    init {
        acceptIntent(DetailIntent.GetMovieDetail(detailArgs.movieId.toInt()))
        acceptIntent(DetailIntent.GetIsMovieFavorite(detailArgs.movieId.toInt()))
    }

    override fun mapIntents(intent: DetailIntent): Flow<DetailUiState.PartialState> =
        when (intent) {
            is DetailIntent.ChangeTab -> flowOf(DetailUiState.PartialState.ChangeTab(intent.selectedTabIndex))
            is DetailIntent.GetMovieDetail -> getMovieDetail(intent.id)
            is DetailIntent.OnFavoriteClick -> decideFavoriteAction(uiState.value.isFavorite)
            DetailIntent.OnNavigateBackClick -> {
                publishEvent(DetailEvent.NavigateBack)
                emptyFlow()
            }

            is DetailIntent.GetIsMovieFavorite -> getIsMovieFavorite(intent.id)
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

            is DetailUiState.PartialState.IsMovieFavorite -> previousState.copy(
                isFavorite = partialState.isMovieFavorite
            )
        }

    private fun decideFavoriteAction(previousStateOfFavorite: Boolean): Flow<DetailUiState.PartialState> {
        return if (previousStateOfFavorite) {
            deleteFavoriteMovie(uiState.value.movieDetail?.id.orZero())
        } else {
            uiState.value.movieDetail?.let { movieDetail ->
                addMovieToFavorite(movieDetail.toMovie())
            } ?: emptyFlow()
        }
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

    private fun getIsMovieFavorite(id: Int): Flow<DetailUiState.PartialState> = flow {
        isMovieFavoriteUseCase(id = id)
            .asResult()
            .map {
                when (it) {
                    is Result.Success -> emit(DetailUiState.PartialState.IsMovieFavorite(it.data))
                    else -> emit(DetailUiState.PartialState.IsMovieFavorite(false))
                }
            }
            .catch {
                emit(DetailUiState.PartialState.Error(it.message.orEmpty()))
            }
            .collect()
    }

    private fun deleteFavoriteMovie(id: Int): Flow<DetailUiState.PartialState> = flow {
        deleteFavoriteMovieUseCase(id = id)
        emit(DetailUiState.PartialState.IsMovieFavorite(false))
    }

    private fun addMovieToFavorite(movie: Movie): Flow<DetailUiState.PartialState> = flow {
        addMovieToFavoriteUseCase(movie)
        emit(DetailUiState.PartialState.IsMovieFavorite(true))
    }

}