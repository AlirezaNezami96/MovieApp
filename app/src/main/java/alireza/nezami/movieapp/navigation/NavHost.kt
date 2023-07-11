package alireza.nezami.movieapp.navigation

import alireza.nezami.favorite.navigation.favoriteScreen
import alireza.nezami.home.navigation.homeNavigationRoute
import alireza.nezami.home.navigation.moviesScreen
import alireza.nezami.movieapp.ui.AppState
import alireza.nezami.search.navigation.navigateToSearch
import alireza.nezami.search.navigation.searchScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost

/**
 * Top-level navigation graph.
 *
 * The navigation graph defined in this file defines the different top level routes. Navigation
 * within each route is handled using state and Back Handlers.
 */
@Composable
fun AppNavHost(
    appState: AppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = homeNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        moviesScreen(
            onMovieClick = {},
            onSearchClick = {
                navController.navigateToSearch()
            }
        )

        searchScreen(
            onMovieClick = {}
        )

        favoriteScreen(
            onMovieClick = {}
        )
    }
}
