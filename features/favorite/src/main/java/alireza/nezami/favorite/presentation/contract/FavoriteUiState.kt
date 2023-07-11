package alireza.nezami.favorite.presentation.contract

import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
import alireza.nezami.model.movie.MovieState
import alireza.nezami.model.movie.Movies
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class FavoriteUiState(
    val favoriteMoviesList: List<Movie> = emptyList(),
    val favoriteListState: MovieState = MovieState(state = ListState.LOADING)
) : Parcelable {

    sealed class PartialState {
        data class AddFavoriteMovieList(val movies: List<Movie>) : PartialState()
        data class Error(val errorMessage: String) : PartialState()
        data class Loading(val shown: Boolean) : PartialState()
    }
}
