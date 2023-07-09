package alireza.nezami.movieapp.ui.theme

import alireza.nezami.designsystem.theme.AppTypography
import alireza.nezami.designsystem.theme.BackgroundTheme
import alireza.nezami.designsystem.theme.Blue70
import alireza.nezami.designsystem.theme.DarkBlue60
import alireza.nezami.designsystem.theme.DarkPurple90
import alireza.nezami.designsystem.theme.LocalBackgroundTheme
import alireza.nezami.designsystem.theme.LocalTintTheme
import alireza.nezami.designsystem.theme.Orange80
import alireza.nezami.designsystem.theme.TintTheme
import alireza.nezami.designsystem.theme.White70
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Light default theme color scheme
 */
val LightDefaultColorScheme = lightColorScheme(
    background = DarkPurple90,
    surface = DarkBlue60,
    onBackground = Color.White,
    onSurface = White70,
    primary = Blue70,
    secondary = Orange80
)

/**
 * Dark default theme color scheme
 */
val DarkDefaultColorScheme = darkColorScheme(
    background = DarkPurple90,
    surface = DarkBlue60,
    onBackground = Color.White,
    onSurface = White70,
    primary = Blue70,
    secondary = Orange80
)

/**
 * Light Android theme color scheme
 */
val LightAndroidColorScheme = lightColorScheme(
    background = DarkPurple90,
    surface = DarkBlue60,
    onBackground = Color.White,
    onSurface = White70,
    primary = Blue70,
    secondary = Orange80
)

/**
 * Dark Android theme color scheme
 */
val DarkAndroidColorScheme = darkColorScheme(
    background = DarkPurple90,
    surface = DarkBlue60,
    onBackground = Color.White,
    onSurface = White70,
    primary = Blue70,
    secondary = Orange80
)

/**
 * Light Android background theme
 */
val LightAndroidBackgroundTheme = BackgroundTheme(color = DarkPurple90)

/**
 * Dark Android background theme
 */
val DarkAndroidBackgroundTheme = BackgroundTheme(color = DarkPurple90)

/**
 * App Theme.
 *
 * @param darkTheme Whether the theme should use a dark color scheme (follows system by default).
 * @param androidTheme Whether the theme should use the Android theme color scheme instead of the
 *        default theme.
 * @param disableDynamicTheming If `true`, disables the use of dynamic theming, even when it is
 *        supported. This parameter has no effect if [androidTheme] is `true`.
 */
@Composable
fun MovieAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    androidTheme: Boolean = false,
    disableDynamicTheming: Boolean = true,
    content: @Composable () -> Unit,
) {
    // Color scheme
    val colorScheme = when {
        androidTheme -> if (darkTheme) DarkAndroidColorScheme else LightAndroidColorScheme
        else -> if (darkTheme) DarkDefaultColorScheme else LightDefaultColorScheme
    }
    // Background theme
    val defaultBackgroundTheme = BackgroundTheme(
        color = colorScheme.background,
        tonalElevation = 2.dp,
    )
    val backgroundTheme = when {
        androidTheme -> if (darkTheme) DarkAndroidBackgroundTheme else LightAndroidBackgroundTheme
        else -> defaultBackgroundTheme
    }
    val tintTheme = when {
        androidTheme -> TintTheme()
        !disableDynamicTheming && supportsDynamicTheming() -> TintTheme(colorScheme.primary)
        else -> TintTheme()
    }
    // Composition locals
    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme,
        LocalTintTheme provides tintTheme,
    ) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content,
    )
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
