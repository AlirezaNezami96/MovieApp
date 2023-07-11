package alireza.nezami.search.presentation

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.component.EmptySearchResult
import alireza.nezami.designsystem.component.HeightSpacer
import alireza.nezami.designsystem.component.SearchInput
import alireza.nezami.designsystem.component.SearchMovieCard
import alireza.nezami.designsystem.extensions.collectWithLifecycle
import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
import alireza.nezami.model.movie.MovieState
import alireza.nezami.search.presentation.contract.SearchEvent
import alireza.nezami.search.presentation.contract.SearchIntent
import alireza.nezami.search.presentation.contract.SearchUiState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    HandleEvents(events = viewModel.event, navigateToMovieDetail = onMovieClick)

    SearchContent(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun SearchContent(uiState: SearchUiState, onIntent: (SearchIntent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
            )
    ) {
        SearchContent(
            text = uiState.searchQuery,
            isLoading = uiState.searchResultState.state == ListState.LOADING,
            onIntent = onIntent
        )

        HeightSpacer(value = 8)

        MoviesListContent(
            listState = uiState.searchResultState,
            list = uiState.searchResultList,
            onPaginate = {
                onIntent.invoke(SearchIntent.Paginate)
            },
            onMovieCardClick = {
                onIntent.invoke(SearchIntent.OnMovieClick(it))
            }
        )
    }
}

@Composable
fun LoadingComponent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun MoviesListContent(
    listState: MovieState,
    list: List<Movie>,
    onPaginate: () -> Unit,
    onMovieCardClick: (id: Int) -> Unit
) {
    val lazyListThreshold = 6
    val lazyListState = rememberLazyListState()
    val shouldStartPaginate = remember {
        derivedStateOf {
            (lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                ?: -9) >= (lazyListState.layoutInfo.totalItemsCount - lazyListThreshold)
        }
    }

    LaunchedEffect(key1 = shouldStartPaginate.value) {
        if (shouldStartPaginate.value && listState.state == ListState.IDLE)
            onPaginate()
    }

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        itemsIndexed(
            items = list,
            key = { _, data -> data.id }
        ) { index, movie ->
            SearchMovieCard(
                position = index,
                movieTitle = movie.title,
                moviePosterUrl = movie.posterPath,
                movieRating = movie.voteAverage,
                movieGenres = movie.genreNames,
                releaseDate = movie.releaseDate,
                voteCount = movie.voteCount,
                onMovieCardClick = {
                    onMovieCardClick(movie.id)
                }
            )
        }

        item(
            key = listState,
        ) {
            when (listState.state) {
                ListState.LOADING, ListState.PAGINATING -> LoadingComponent()
                ListState.EMPTY -> EmptySearchResult(modifier = Modifier.fillMaxHeight())
                ListState.ERROR -> PaginationErrorText(listState.errorMessage)
                ListState.PAGINATION_EXHAUST -> PaginationErrorText(stringResource(R.string.nothing_left_to_show))
                else -> {}
            }
        }
    }
}

@Composable
fun PaginationErrorText(text: String) {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .border(
                width = 1.dp,
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primary
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
        )
    }
}

@Composable
fun SearchContent(
    text: String,
    isLoading: Boolean,
    onIntent: (SearchIntent) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    SearchInput(
        text = text,
        enabled = true,
        isLoading = isLoading,
        focusRequester = focusRequester,
        onValueChange = {
            onIntent.invoke(SearchIntent.EnterSearchQuery(it))
        })
}

@Composable
private fun HandleEvents(
    events: Flow<SearchEvent>,
    navigateToMovieDetail: (Int) -> Unit
) {
    events.collectWithLifecycle {
        when (it) {
            is SearchEvent.NavigateToMovieDetail -> navigateToMovieDetail(it.id)
        }
    }
}
