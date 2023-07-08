package alireza.nezami.home.presentation.movieList.contract


sealed class MoviesEvent {
    data class NavigateTo(val route: String) : MoviesEvent()
}
