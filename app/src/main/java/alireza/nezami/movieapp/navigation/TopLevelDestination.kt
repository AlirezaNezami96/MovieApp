package alireza.nezami.movieapp.navigation

import alireza.nezami.ui.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector

enum class TopLevelDestination(
    val selectedIcon: ImageVector,
    val iconTextId: Int,
) {
    HOME(
        selectedIcon = Icons.Rounded.Home,
        iconTextId = R.string.home,
    ),
    SEARCH(
        selectedIcon = Icons.Rounded.Search,
        iconTextId = R.string.search,
    ),
    FAVORITE(
        selectedIcon = Icons.Rounded.Favorite,
        iconTextId = R.string.favorite,
    ),
}
