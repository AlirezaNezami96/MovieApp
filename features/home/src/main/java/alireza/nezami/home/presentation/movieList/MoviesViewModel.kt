package alireza.nezami.home.presentation.movieList

import alireza.nezami.common.base.BaseViewModel
import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.domain.usecase.GetNowPlayingMoviesUseCase
import alireza.nezami.domain.usecase.GetPopularMoviesUseCase
import alireza.nezami.domain.usecase.GetTopRatedMoviesUseCase
import alireza.nezami.domain.usecase.GetUpcomingMoviesUseCase
import alireza.nezami.home.presentation.movieList.contract.MoviesEvent
import alireza.nezami.home.presentation.movieList.contract.MoviesIntent
import alireza.nezami.home.presentation.movieList.contract.MoviesTabState
import alireza.nezami.home.presentation.movieList.contract.MoviesUiState
import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
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
class MoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    moviesUiState: MoviesUiState,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
) : BaseViewModel<MoviesUiState, MoviesUiState.PartialState, MoviesEvent, MoviesIntent>(
    savedStateHandle,
    moviesUiState
) {

    init {
        acceptIntent(MoviesIntent.GetNowPlaying(uiState.value.popularMovieState.page))
    }

    override fun mapIntents(intent: MoviesIntent): Flow<MoviesUiState.PartialState> =
        when (intent) {
            is MoviesIntent.ChangeTab -> {
                decideTabAction(intent.selectedTabIndex)
                flowOf(MoviesUiState.PartialState.ChangeTab(intent.selectedTabIndex))
            }

            is MoviesIntent.GetNowPlaying -> getNowPlayingMovies(
                intent.page ?: uiState.value.nowPlayingMovieState.nextPage
            )

            is MoviesIntent.GetPopular -> getPopularMovies(
                intent.page ?: uiState.value.popularMovieState.nextPage
            )

            is MoviesIntent.GetTopRated -> getTopRatedMovies(
                intent.page ?: uiState.value.topRatedMovieState.nextPage
            )

            is MoviesIntent.GetUpcoming -> getUpcomingMovies(
                intent.page ?: uiState.value.popularMovieState.nextPage
            )

            is MoviesIntent.OnMovieClick -> TODO()
            MoviesIntent.OnSearchClick -> {
                publishEvent(MoviesEvent.NavigateToSearch)
                emptyFlow()
            }
        }

    override fun reduceUiState(
        previousState: MoviesUiState,
        partialState: MoviesUiState.PartialState
    ): MoviesUiState =
        when (partialState) {
            is MoviesUiState.PartialState.AddNowPlayingMovies -> previousState.copy(
                nowPlayingMovieState = previousState.nowPlayingMovieState.copy(
                    page = partialState.movies.page,
                    state = ListState.IDLE
                ),
                nowPlayingMovies = addMoviesToList(
                    previousState.nowPlayingMovies,
                    partialState.movies.results
                )
            )

            is MoviesUiState.PartialState.AddPopularMovies -> previousState.copy(
                popularMovieState = previousState.popularMovieState.copy(
                    page = partialState.movies.page,
                    state = ListState.IDLE
                ),
                popularMovies = addMoviesToList(
                    previousState.popularMovies,
                    partialState.movies.results
                )

            )

            is MoviesUiState.PartialState.AddTopRatedMovies -> previousState.copy(
                topRatedMovieState = previousState.topRatedMovieState.copy(
                    page = partialState.movies.page,
                    state = ListState.IDLE
                ),
                topRatedMovies = addMoviesToList(
                    previousState.topRatedMovies,
                    partialState.movies.results
                )
            )

            is MoviesUiState.PartialState.AddUpcomingMovies -> previousState.copy(
                upcomingMovieState = previousState.upcomingMovieState.copy(
                    page = partialState.movies.page,
                    state = ListState.IDLE
                ),
                upcomingMovies = addMoviesToList(
                    previousState.upcomingMovies,
                    partialState.movies.results
                )
            )

            is MoviesUiState.PartialState.PopularLoading -> previousState.copy(
                popularMovieState = previousState.popularMovieState.copy(
                    state = if (previousState.popularMovies.isEmpty()) ListState.LOADING
                    else ListState.PAGINATING
                )
            )

            is MoviesUiState.PartialState.PopularError -> previousState.copy(
                popularMovieState = previousState.popularMovieState.copy(
                    state = ListState.ERROR,
                    errorMessage = partialState.message
                )
            )

            is MoviesUiState.PartialState.ChangeTab -> previousState.copy(
                selectedTabIndex = partialState.selectedTabIndex
            )

            is MoviesUiState.PartialState.NowPlayingError -> previousState.copy(
                popularMovieState = previousState.nowPlayingMovieState.copy(
                    state = ListState.ERROR,
                    errorMessage = partialState.message
                )
            )

            is MoviesUiState.PartialState.NowPlayingLoading -> previousState.copy(
                popularMovieState = previousState.nowPlayingMovieState.copy(
                    state = if (previousState.nowPlayingMovies.isEmpty()) ListState.LOADING
                    else ListState.PAGINATING
                )
            )

            is MoviesUiState.PartialState.TopRateError -> previousState.copy(
                popularMovieState = previousState.topRatedMovieState.copy(
                    state = ListState.ERROR,
                    errorMessage = partialState.message
                )
            )

            is MoviesUiState.PartialState.TopRateLoading -> previousState.copy(
                popularMovieState = previousState.topRatedMovieState.copy(
                    state = if (previousState.topRatedMovies.isEmpty()) ListState.LOADING
                    else ListState.PAGINATING
                )
            )

            is MoviesUiState.PartialState.UpcomingError -> previousState.copy(
                popularMovieState = previousState.upcomingMovieState.copy(
                    state = ListState.ERROR,
                    errorMessage = partialState.message
                )
            )

            is MoviesUiState.PartialState.UpcomingLoading -> previousState.copy(
                popularMovieState = previousState.upcomingMovieState.copy(
                    state = if (previousState.upcomingMovies.isEmpty()) ListState.LOADING
                    else ListState.PAGINATING
                )
            )
        }


    private fun decideTabAction(selectedTabIndex: Int) {
        with(uiState.value) {
            when {
                selectedTabIndex == MoviesTabState.TopRated.index && topRatedMovies.isEmpty() -> {
                    acceptIntent(MoviesIntent.GetTopRated(uiState.value.popularMovieState.page))
                }

                selectedTabIndex == MoviesTabState.NowPlaying.index && nowPlayingMovies.isEmpty() -> {
                    acceptIntent(MoviesIntent.GetNowPlaying(uiState.value.nowPlayingMovieState.page))
                }

                selectedTabIndex == MoviesTabState.Upcoming.index && upcomingMovies.isEmpty() -> {
                    acceptIntent(MoviesIntent.GetUpcoming(uiState.value.upcomingMovieState.page))
                }

                selectedTabIndex == MoviesTabState.Popular.index && popularMovies.isEmpty() -> {
                    acceptIntent(MoviesIntent.GetPopular(uiState.value.popularMovieState.page))
                }

                else -> {}
            }
        }
    }

    private fun addMoviesToList(previousList: List<Movie>, newList: List<Movie>): List<Movie> {
        val finalList = previousList.toMutableList().apply {
            addAll(newList)
        }
        return finalList
    }


    private fun getPopularMovies(page: Int): Flow<MoviesUiState.PartialState> = flow {
        getPopularMoviesUseCase(page)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(MoviesUiState.PartialState.PopularError(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(MoviesUiState.PartialState.PopularLoading(true))
                    is Result.Success -> emit(MoviesUiState.PartialState.AddPopularMovies(it.data))
                }
            }
            .catch {
                emit(MoviesUiState.PartialState.PopularError(it.message.orEmpty()))
            }
            .collect()
    }

    private fun getNowPlayingMovies(page: Int): Flow<MoviesUiState.PartialState> = flow {
        getNowPlayingMoviesUseCase(page)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(MoviesUiState.PartialState.NowPlayingError(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(MoviesUiState.PartialState.NowPlayingLoading(true))
                    is Result.Success -> emit(MoviesUiState.PartialState.AddNowPlayingMovies(it.data))
                }
            }
            .catch {
                emit(MoviesUiState.PartialState.NowPlayingError(it.message.orEmpty()))
            }
            .collect()
    }

    private fun getUpcomingMovies(page: Int): Flow<MoviesUiState.PartialState> = flow {
        getUpcomingMoviesUseCase(page)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(MoviesUiState.PartialState.UpcomingError(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(MoviesUiState.PartialState.UpcomingLoading(true))
                    is Result.Success -> emit(MoviesUiState.PartialState.AddUpcomingMovies(it.data))
                }
            }
            .catch {
                emit(MoviesUiState.PartialState.UpcomingError(it.message.orEmpty()))
            }
            .collect()
    }

    private fun getTopRatedMovies(page: Int): Flow<MoviesUiState.PartialState> = flow {
        getTopRatedMoviesUseCase(page)
            .asResult()
            .map {
                when (it) {
                    is Result.Error -> emit(MoviesUiState.PartialState.TopRateError(it.exception?.message.orEmpty()))
                    Result.Loading -> emit(MoviesUiState.PartialState.TopRateLoading(true))
                    is Result.Success -> emit(MoviesUiState.PartialState.AddTopRatedMovies(it.data))
                }
            }
            .catch {
                emit(MoviesUiState.PartialState.TopRateError(it.message.orEmpty()))
            }
            .collect()
    }

}