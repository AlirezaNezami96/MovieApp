package alireza.nezami.favorite.presentation.contract

sealed class FavoriteIntent {
    object GetFavoriteMovies : FavoriteIntent()
    data class OnMovieClick(val id: Int) : FavoriteIntent()
}
