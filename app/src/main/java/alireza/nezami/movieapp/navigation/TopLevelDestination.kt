package alireza.nezami.movieapp.navigation

import alireza.nezami.designsystem.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    HOME(
        selectedIcon = Icons.Rounded.Home,
        iconTextId = R.string.home,
        titleTextId = R.string.what_do_you_want_to_watch
    ),
    SEARCH(
        selectedIcon = Icons.Rounded.Search,
        iconTextId = R.string.search,
        titleTextId = R.string.search,
    ),
    FAVORITE(
        selectedIcon = Icons.Rounded.Favorite,
        iconTextId = R.string.favorite,
        titleTextId = R.string.favorite,
    ),
}
