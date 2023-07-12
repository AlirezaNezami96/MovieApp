package alireza.nezami.designsystem.component

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.theme.LocalTintTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

/**
 * A wrapper around [AsyncImage] which determines the colorFilter based on the theme
 */
@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    val iconTint = LocalTintTheme.current.iconTint
    AsyncImage(
        placeholder = painterResource(R.drawable.ic_movie),
        model = imageUrl,
        contentDescription = contentDescription,
        colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        error = painterResource(R.drawable.ic_movie)

    )
}
