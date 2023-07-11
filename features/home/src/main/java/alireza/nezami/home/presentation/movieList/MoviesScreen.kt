package alireza.nezami.home.presentation.movieList

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.component.HeightSpacer
import alireza.nezami.designsystem.component.MovieCard
import alireza.nezami.designsystem.component.SearchInput
import alireza.nezami.designsystem.component.Tab
import alireza.nezami.designsystem.component.ScrollableTabRow
import alireza.nezami.designsystem.extensions.collectWithLifecycle
import alireza.nezami.home.presentation.movieList.contract.MoviesEvent
import alireza.nezami.home.presentation.movieList.contract.MoviesIntent
import alireza.nezami.home.presentation.movieList.contract.MoviesTabState
import alireza.nezami.home.presentation.movieList.contract.MoviesUiState
import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
import alireza.nezami.model.movie.MovieState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
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
fun MoviesScreen(
    viewModel: MoviesViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsState()

    HandleEvents(
        events = viewModel.event,
        navigateToMovieDetail = onMovieClick,
        navigateToSearch = onSearchClick
    )

    MoviesContent(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun MoviesContent(uiState: MoviesUiState, onIntent: (MoviesIntent) -> Unit) {
    Column(
        modifier = Modifier
            .padding(0.dp)
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
            )
    ) {
        SearchContent(onIntent)

        TabContent(uiState, onIntent)

        MoviesListContent(
            shown = uiState.selectedTabIndex == MoviesTabState.NowPlaying.index,
            listState = uiState.nowPlayingMovieState,
            list = uiState.nowPlayingMovies,
            onPaginate = {
                onIntent.invoke(MoviesIntent.GetNowPlaying())
            },
            onMovieCardClick = {
                onIntent.invoke(MoviesIntent.OnMovieClick(it))
            }
        )
        MoviesListContent(
            shown = uiState.selectedTabIndex == MoviesTabState.Upcoming.index,
            listState = uiState.upcomingMovieState,
            list = uiState.upcomingMovies,
            onPaginate = {
                onIntent.invoke(MoviesIntent.GetUpcoming())
            },
            onMovieCardClick = {
                onIntent.invoke(MoviesIntent.OnMovieClick(it))
            }
        )

        MoviesListContent(
            shown = uiState.selectedTabIndex == MoviesTabState.TopRated.index,
            listState = uiState.topRatedMovieState,
            list = uiState.topRatedMovies,
            onPaginate = {
                onIntent.invoke(MoviesIntent.GetTopRated())
            },
            onMovieCardClick = {
                onIntent.invoke(MoviesIntent.OnMovieClick(it))
            }
        )

        MoviesListContent(
            shown = uiState.selectedTabIndex == MoviesTabState.Popular.index,
            listState = uiState.popularMovieState,
            list = uiState.popularMovies,
            onPaginate = {
                onIntent.invoke(MoviesIntent.GetPopular())
            },
            onMovieCardClick = {
                onIntent.invoke(MoviesIntent.OnMovieClick(it))
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
    onMovieCardClick: (id: Int) -> Unit,
    shown: Boolean
) {
    if (shown) {
        val lazyListThreshold = 6
        val lazyGridState = rememberLazyGridState()
        val shouldStartPaginate = remember {
            derivedStateOf {
                (lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                    ?: -9) >= (lazyGridState.layoutInfo.totalItemsCount - lazyListThreshold)
            }
        }

        LaunchedEffect(key1 = shouldStartPaginate.value) {
            if (shouldStartPaginate.value && listState.state == ListState.IDLE)
                onPaginate()
        }

        LazyVerticalGrid(
            state = lazyGridState,
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            itemsIndexed(
                items = list,
                key = { _, data -> data.id }
            ) { index, movie ->
                MovieCard(
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

                    ListState.ERROR -> PaginationErrorText(listState.errorMessage)
                    ListState.PAGINATION_EXHAUST -> PaginationErrorText(stringResource(R.string.nothing_left_to_show))
                    else -> {}
                }
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
fun SearchContent(onIntent: (MoviesIntent) -> Unit) {
    val focusRequester = remember { FocusRequester() }

    SearchInput(
        focusRequester = focusRequester,
        onParentClick = {
            onIntent.invoke(MoviesIntent.OnSearchClick)
        })
}

@Composable
private fun TabContent(uiState: MoviesUiState, onIntent: (MoviesIntent) -> Unit) {
    HeightSpacer(value = 8)

    val titles = listOf(
        R.string.now_playing,
        R.string.upcoming,
        R.string.top_rated,
        R.string.popular,
    )
    ScrollableTabRow(selectedTabIndex = uiState.selectedTabIndex) {
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

@Composable
private fun HandleEvents(
    events: Flow<MoviesEvent>,
    navigateToMovieDetail: (Int) -> Unit,
    navigateToSearch: () -> Unit
) {
    events.collectWithLifecycle {
        when (it) {
            MoviesEvent.NavigateToSearch -> navigateToSearch()
            is MoviesEvent.NavigateToMovieDetail -> navigateToMovieDetail(it.id)
        }
    }
}
