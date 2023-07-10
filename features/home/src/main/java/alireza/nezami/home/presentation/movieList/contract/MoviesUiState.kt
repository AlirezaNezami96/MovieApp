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

    val topRatedMoviePage: MovieState = MovieState.DEFAULT,
    val upcomingMoviePage: MovieState = MovieState.DEFAULT,
    val popularMoviePage: MovieState = MovieState.DEFAULT,
    val nowPlayingMoviePage: MovieState = MovieState.DEFAULT,

    val selectedTabIndex: Int = MoviesTabState.NowPlaying.index,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
) : Parcelable {

    sealed class PartialState {
        data class AddTopRatedMovies(val topRatedMovies: Movies) : PartialState()
        data class AddUpcomingMovies(val topRatedMovies: Movies) : PartialState()
        data class AddPopularMovies(val moviePagingData: Movies) : PartialState()
        data class AddNowPlayingMovies(val topRatedMovies: Movies) : PartialState()
        data class ShowErrorDialog(val message: String) : PartialState()
        data class Loading(val show: Boolean) : PartialState()
        data class ChangeTab(val selectedTabIndex: Int) : PartialState()

    }
}
