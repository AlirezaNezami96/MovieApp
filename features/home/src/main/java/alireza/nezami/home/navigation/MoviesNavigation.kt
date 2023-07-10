package alireza.nezami.home.navigation

import alireza.nezami.home.presentation.movieList.MoviesScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val moviesNavigationRoute = "home_route"

fun NavController.navigateToMovies(navOptions: NavOptions? = null) {
    this.navigate(moviesNavigationRoute, navOptions)
}

fun NavGraphBuilder.moviesScreen(
    onMovieClick: (Int) -> Unit,
    onSearchClick: () -> Unit
) {
    composable(
        route = moviesNavigationRoute,
    ) {
        MoviesScreen(onMovieClick = onMovieClick, onSearchClick = onSearchClick)
    }
}
