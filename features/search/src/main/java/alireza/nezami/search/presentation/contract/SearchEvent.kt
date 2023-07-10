package alireza.nezami.search.presentation.contract


sealed class SearchEvent {
    data class NavigateTo(val route: String) : SearchEvent()
}
