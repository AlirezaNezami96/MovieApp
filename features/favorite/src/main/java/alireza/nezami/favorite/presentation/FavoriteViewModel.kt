package alireza.nezami.favorite.presentation

import alireza.nezami.common.base.BaseViewModel
import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.domain.usecase.GetFavoriteMoviesUseCase
import alireza.nezami.favorite.presentation.contract.FavoriteEvent
import alireza.nezami.favorite.presentation.contract.FavoriteIntent
import alireza.nezami.favorite.presentation.contract.FavoriteUiState
import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.MovieState
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    SearchUiState: FavoriteUiState,
    private val favoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel<FavoriteUiState, FavoriteUiState.PartialState, FavoriteEvent, FavoriteIntent>(
    savedStateHandle,
    SearchUiState
) {

    init {
        acceptIntent(FavoriteIntent.GetFavoriteMovies)
    }

    override fun mapIntents(intent: FavoriteIntent): Flow<FavoriteUiState.PartialState> =
        when (intent) {
            is FavoriteIntent.OnMovieClick -> {
                publishEvent(FavoriteEvent.NavigateToMovieDetail(intent.id))
                emptyFlow()
            }

            FavoriteIntent.GetFavoriteMovies -> getFavoriteMovies()
        }

    override fun reduceUiState(
        previousState: FavoriteUiState,
        partialState: FavoriteUiState.PartialState
    ): FavoriteUiState =
        when (partialState) {
            is FavoriteUiState.PartialState.AddFavoriteMovieList -> previousState.copy(
                favoriteMoviesList = partialState.movies,
                favoriteListState = MovieState(
                    state = if (partialState.movies.isNotEmpty()) ListState.IDLE
                    else ListState.EMPTY
                )
            )

            is FavoriteUiState.PartialState.Error -> previousState.copy(
                favoriteListState = previousState.favoriteListState.copy(
                    state = ListState.ERROR,
                    errorMessage = partialState.errorMessage
                )
            )

            is FavoriteUiState.PartialState.Loading -> previousState.copy(
                favoriteListState = previousState.favoriteListState.copy(
                    state = ListState.LOADING
                )
            )
        }


    private fun getFavoriteMovies(): Flow<FavoriteUiState.PartialState> = flow {
        favoriteMoviesUseCase()
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(FavoriteUiState.PartialState.Error(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(FavoriteUiState.PartialState.Loading(true))
                    is Result.Success -> emit(FavoriteUiState.PartialState.AddFavoriteMovieList(it.data))
                }
            }
            .catch {
                emit(FavoriteUiState.PartialState.Error(it.message.orEmpty()))
            }
            .collect()
    }

}