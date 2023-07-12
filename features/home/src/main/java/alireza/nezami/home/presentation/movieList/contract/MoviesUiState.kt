package alireza.nezami.home.presentation.movieList.contract

import alireza.nezami.model.movie.Movie
import alireza.nezami.model.movie.MovieState
import alireza.nezami.model.movie.Movies
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class MoviesUiState(
    val topRatedMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val popularMovies: List<Movie> = emptyList(),
    val nowPlayingMovies: List<Movie> = emptyList(),

    val topRatedMovieState: MovieState = MovieState(),
    val upcomingMovieState: MovieState = MovieState(),
    val popularMovieState: MovieState = MovieState(),
    val nowPlayingMovieState: MovieState = MovieState(),

    val selectedTabIndex: Int = MoviesTabState.NowPlaying.index,

    ) : Parcelable {

    sealed class PartialState {
        data class AddTopRatedMovies(val movies: Movies) : PartialState()
        data class TopRateError(val message: String) : PartialState()
        data class TopRateLoading(val show: Boolean) : PartialState()

        data class AddUpcomingMovies(val movies: Movies) : PartialState()
        data class UpcomingError(val message: String) : PartialState()
        data class UpcomingLoading(val show: Boolean) : PartialState()

        data class AddPopularMovies(val movies: Movies) : PartialState()
        data class PopularError(val message: String) : PartialState()
        data class PopularLoading(val show: Boolean) : PartialState()

        data class AddNowPlayingMovies(val movies: Movies) : PartialState()
        data class NowPlayingError(val message: String) : PartialState()
        data class NowPlayingLoading(val show: Boolean) : PartialState()

        data class ChangeTab(val selectedTabIndex: Int) : PartialState()

    }
}
