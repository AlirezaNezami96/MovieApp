package alireza.nezami.designsystem.component

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.theme.MovieAppTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    enabled: Boolean = false,
    onValueChange: (String) -> Unit = {},
    onParentClick: () -> Unit = {},
) {
    val (text, setText) = remember { mutableStateOf("") }
    val (isFocused, setFocused) = remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                if (!enabled) {
                    onParentClick()
                }
            }
            .background(
                color = MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.2f
                ),
                shape = MaterialTheme.shapes.medium
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextInput(
            enabled = enabled,
            text = text,
            onValueChange = {
                setText(it)
                onValueChange(it)
            },
            onFocusChanged = { isFocused ->
                setFocused(isFocused)
            }
        )

        AnimatedFadeVisibility(visible = isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp)
            )
        }

        AnimatedFadeVisibility(visible = isFocused) {
            CloseIcon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp)
            ) {
                setText("")
            }
        }

        AnimatedFadeVisibility(visible = !isLoading && !isFocused) {
            SearchIcon(
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp)
            )
        }

    }
}

@Composable
fun RowScope.TextInput(
    text: String,
    onValueChange: (String) -> Unit,
    onFocusChanged: (isFocused: Boolean) -> Unit,
    enabled: Boolean
) {
    TextField(
        value = text,
        enabled = enabled,
        onValueChange = onValueChange,
        modifier = Modifier
            .weight(1f)
            .onFocusChanged { onFocusChanged(it.isFocused) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

        ),
        singleLine = true,
        placeholder = {
            Text(
                stringResource(R.string.search)
            )
        }
    )
}

@Composable
fun SearchIcon(
    modifier: Modifier = Modifier
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Default.Search,
        contentDescription = "Search Icon",
        tint = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun CloseIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
            .padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Icon"
        )
    }
}

@ExperimentalAnimationApi
@Composable
fun AnimatedFadeVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable() AnimatedVisibilityScope.() -> Unit
) {
    val enterFadeIn = remember { fadeIn(animationSpec = tween(durationMillis = 300)) }
    val exitFadeOut = remember { fadeOut(animationSpec = tween(durationMillis = 300)) }

    AnimatedVisibility(
        visible = visible,
        enter = enterFadeIn,
        exit = exitFadeOut,
        modifier = modifier,
        content = content
    )
}

@Preview
@Composable
fun SearchInputPreview() {
    MovieAppTheme {
        SearchInput(isLoading = false, onValueChange = {})
    }
}
