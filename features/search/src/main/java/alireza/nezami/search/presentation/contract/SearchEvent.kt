package alireza.nezami.search.presentation.contract


sealed class SearchEvent {
    data class NavigateToMovieDetail(val id: Int) : SearchEvent()
}
