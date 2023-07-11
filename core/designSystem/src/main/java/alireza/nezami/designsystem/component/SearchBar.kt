package alireza.nezami.designsystem.component

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.theme.MovieAppTheme
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    text: String = "",
    isLoading: Boolean = false,
    enabled: Boolean = false,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit = {},
    onParentClick: () -> Unit = {},
) {
    val (isFocused, setFocused) = remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(max = 56.dp)
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
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
            text = text,
            focusRequester = focusRequester,
            onValueChange = {
                onValueChange(it)
            },
            onFocusChanged = { isFocused ->
                setFocused(isFocused)
            },
            enabled = enabled
        )

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(24.dp)
            )
        }

        if (isFocused && !isLoading) {
            CloseIcon(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(24.dp)
            ) {
                onValueChange("")
            }
        }

        if (!isLoading && !isFocused) {
            SearchIcon(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .size(24.dp)
            )
        }

    }
}

@Composable
fun TextInput(
    modifier: Modifier = Modifier,
    text: String,
    focusRequester: FocusRequester,
    onValueChange: (String) -> Unit,
    onFocusChanged: (isFocused: Boolean) -> Unit,
    enabled: Boolean
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        enabled = enabled,
        onValueChange = onValueChange,
        modifier = modifier
            .focusRequester(focusRequester)
            .onFocusChanged { onFocusChanged(it.isFocused) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { focusManager.clearFocus() },
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
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.5f
            ),
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(
                alpha = 0.5f
            )

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
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close Icon"
        )
    }
}

@Preview
@Composable
fun SearchInputPreview() {
    val focusRequester = remember { FocusRequester() }

    MovieAppTheme {
        SearchInput(
            text = "",
            isLoading = false,
            onValueChange = {},
            focusRequester = focusRequester
        )
    }
}
