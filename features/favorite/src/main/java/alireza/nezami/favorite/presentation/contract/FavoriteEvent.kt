package alireza.nezami.favorite.presentation.contract


sealed class FavoriteEvent {
    data class NavigateToMovieDetail(val id: Int) : FavoriteEvent()
}
