package alireza.nezami.home.navigation

import alireza.nezami.home.presentation.movieList.MoviesScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val homeNavigationRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeNavigationRoute, navOptions)
}

fun NavGraphBuilder.moviesScreen(
    onMovieClick: (Int) -> Unit,
    onSearchClick: () -> Unit
) {
    composable(
        route = homeNavigationRoute,
    ) {
        MoviesScreen(onMovieClick = onMovieClick, onSearchClick = onSearchClick)
    }
}
