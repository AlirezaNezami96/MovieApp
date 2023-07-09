package alireza.nezami.home.presentation.movieList

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.component.HeightSpacer
import alireza.nezami.designsystem.component.SearchInput
import alireza.nezami.designsystem.component.Tab
import alireza.nezami.designsystem.component.TabRow
import alireza.nezami.home.presentation.movieList.contract.MoviesIntent
import alireza.nezami.home.presentation.movieList.contract.MoviesUiState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    MoviesListContent(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun MoviesListContent(uiState: MoviesUiState, onIntent: (MoviesIntent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 16.dp
        )
    ) {
        searchContent(onIntent)
        tabContent(uiState, onIntent)
    }
}

fun LazyListScope.searchContent(onIntent: (MoviesIntent) -> Unit) {

    item {
        SearchInput(onParentClick = {
            onIntent.invoke(MoviesIntent.OnSearchClick)
        })
    }
}

private fun LazyListScope.tabContent(uiState: MoviesUiState, onIntent: (MoviesIntent) -> Unit) {
    item {
        HeightSpacer(value = 16)
    }

    item {
        val titles = listOf(
            R.string.now_playing,
            R.string.upcoming,
            R.string.top_rated,
            R.string.popular,
        )
        TabRow(selectedTabIndex = uiState.selectedTabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.selectedTabIndex == index,
                    onClick = { onIntent.invoke(MoviesIntent.ChangeTab(index)) },
                    text = {
                        Text(text = stringResource(title))
                    },
                )
            }
        }
    }
}
