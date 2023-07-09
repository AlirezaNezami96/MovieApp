package alireza.nezami.home.presentation.movieList.contract

import alireza.nezami.model.movie.Movies
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class MoviesUiState(
    val topRatedMovies: Movies = Movies.EMPTY,
    val upcomingMovies: Movies = Movies.EMPTY,
    val popularMovies: Movies = Movies.EMPTY,
    val nowPlayingMovies: Movies = Movies.EMPTY,

    val selectedTabIndex : Int = MoviesTabState.NowPlaying.index,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
) : Parcelable {

    sealed class PartialState {
        data class AddTopRatedMovies(val topRatedMovies: Movies) : PartialState()
        data class AddUpcomingMovies(val topRatedMovies: Movies) : PartialState()
        data class AddPopularMovies(val topRatedMovies: Movies) : PartialState()
        data class AddNowPlayingMovies(val topRatedMovies: Movies) : PartialState()
        data class ShowErrorDialog(val message: String) : PartialState()
        data class Loading(val show: Boolean) : PartialState()
        data class ChangeTab(val selectedTabIndex: Int) : PartialState()

    }
}
