package alireza.nezami.home.presentation.movieList.contract


sealed class MoviesEvent {
    object NavigateToSearch : MoviesEvent()
    data class NavigateToMovieDetail(val id: Int) : MoviesEvent()
}
