package alireza.nezami.home.navigation

import alireza.nezami.home.presentation.movieList.MoviesScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val moviesNavigationRoute = "movies_route"

fun NavController.navigateToMovies(navOptions: NavOptions? = null) {
    this.navigate(moviesNavigationRoute, navOptions)
}
//
//fun NavGraphBuilder.moviesScreen(onMovieClick: (Int) -> Unit) {
//    composable(
//        route = moviesNavigationRoute,
//    ) {
////        MoviesScreen(onMovieClick)
//    }
//}
