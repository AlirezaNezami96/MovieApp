package alireza.nezami.favorite.navigation

import alireza.nezami.favorite.presentation.FavoriteScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val favoriteNavigationRoute = "favorite_route"

fun NavController.navigateToFavorite(navOptions: NavOptions? = null) {
    this.navigate(favoriteNavigationRoute, navOptions)
}

fun NavGraphBuilder.favoriteScreen(onMovieClick: (Int) -> Unit) {
    composable(
        route = favoriteNavigationRoute,
    ) {
        FavoriteScreen(onMovieClick = onMovieClick)
    }
}
