package alireza.nezami.home.presentation.movieList.contract

sealed class MoviesIntent {
    data class GetNowPlaying(val page: Int? = null) : MoviesIntent()
    data class GetPopular(val page: Int? = null) : MoviesIntent()
    data class GetTopRated(val page: Int? = null) : MoviesIntent()
    data class GetUpcoming(val page: Int? = null) : MoviesIntent()
    data class OnMovieClick(val id: Int) : MoviesIntent()
    object ChangeTab : MoviesIntent()
    object OnSearchClick : MoviesIntent()
}
