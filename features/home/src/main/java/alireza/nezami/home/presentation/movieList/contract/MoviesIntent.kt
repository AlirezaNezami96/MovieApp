package alireza.nezami.home.presentation.movieList.contract

sealed class MoviesIntent {
    data class GetNowPlaying(val page: Int? = null) : MoviesIntent()
    data class GetPopular(val page: Int? = null) : MoviesIntent()
    data class GetTopRated(val page: Int? = null) : MoviesIntent()
    data class GetUpcoming(val page: Int? = null) : MoviesIntent()
    data class OnMovieClick(val id: Int) : MoviesIntent()
    data class ChangeTab(val selectedTabIndex: Int) : MoviesIntent()
    object OnSearchClick : MoviesIntent()
}
