package alireza.nezami.movieapp.navigation

import alireza.nezami.home.navigation.moviesNavigationRoute
import alireza.nezami.home.presentation.movieList.MoviesScreen
import alireza.nezami.movieapp.ui.AppState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

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
    startDestination: String = moviesNavigationRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        //todo: handle onMovieClick
        composable(
            route = moviesNavigationRoute,
        ) {
            MoviesScreen() {

            }
        }
//        moviesScreen(onMovieClick = {})

        //todo: add more routes
    }
}
