package alireza.nezami.search.navigation

import alireza.nezami.search.presentation.SearchScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val searchNavigationRoute = "search_route"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(onMovieClick: (Int) -> Unit) {
    composable(
        route = searchNavigationRoute,
    ) {
        SearchScreen(onMovieClick = onMovieClick)
    }
}
