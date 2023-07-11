package alireza.nezami.detail.navigation

import alireza.nezami.common.utils.StringDecoder
import alireza.nezami.common.utils.UriDecoder
import alireza.nezami.detail.presentation.DetailScreen
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

internal const val movieIdArg = "movieId"

internal class DetailArgs(val movieId: String) {
    constructor(savedStateHandle: SavedStateHandle) :
            this(UriDecoder().decodeString(checkNotNull(savedStateHandle[movieIdArg])))
}

fun NavController.navigateToDetail(movieId: Int) {
    val encodedId = Uri.encode(movieId.toString())
    this.navigate("detail_route/$encodedId") {
        launchSingleTop = true
    }
}

fun NavGraphBuilder.detailScreen(
    navigateUp: () -> Unit
) {
    composable(
        route = "detail_route/{$movieIdArg}",
        arguments = listOf(
            navArgument(movieIdArg) { type = NavType.StringType },
        )
    ) {
        DetailScreen(
            navigateUp = navigateUp
        )
    }
}
