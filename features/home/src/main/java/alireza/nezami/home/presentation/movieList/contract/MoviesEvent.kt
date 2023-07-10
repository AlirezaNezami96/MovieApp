package alireza.nezami.home.presentation.movieList.contract


sealed class MoviesEvent {
    data class NavigateToSearch(val route: String) : MoviesEvent()
    data class NavigateToMovieDetail(val id: Int) : MoviesEvent()
}
