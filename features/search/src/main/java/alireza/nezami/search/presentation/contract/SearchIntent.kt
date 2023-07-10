package alireza.nezami.search.presentation.contract

sealed class SearchIntent {
    data class EnterSearchQuery(val query: String) : SearchIntent()
    object Paginate : SearchIntent()
    data class OnMovieClick(val id: Int) : SearchIntent()
}
