package alireza.nezami.favorite.presentation

import alireza.nezami.designsystem.component.EmptyFavoriteResult
import alireza.nezami.designsystem.component.MovieCard
import alireza.nezami.designsystem.extensions.collectWithLifecycle
import alireza.nezami.favorite.presentation.contract.FavoriteEvent
import alireza.nezami.favorite.presentation.contract.FavoriteIntent
import alireza.nezami.favorite.presentation.contract.FavoriteUiState
import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    onMovieClick: (Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    HandleEvents(events = viewModel.event, navigateToMovieDetail = onMovieClick)

    FavoriteContent(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun FavoriteContent(uiState: FavoriteUiState, onIntent: (FavoriteIntent) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        when (uiState.favoriteListState.state) {
            ListState.LOADING -> LoadingComponent()

            ListState.EMPTY, ListState.ERROR -> EmptyFavoriteResult(
                modifier = Modifier
                    .align(Alignment.Center)
            )

            ListState.IDLE -> MoviesListContent(
                list = uiState.favoriteMoviesList,
                onMovieCardClick = {
                    onIntent.invoke(FavoriteIntent.OnMovieClick(it))
                }
            )

            else -> {}
        }

    }

}

@Composable
fun BoxScope.LoadingComponent() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .size(48.dp)
            .align(Alignment.Center)
    )
}

@Composable
fun MoviesListContent(
    list: List<Movie>,
    onMovieCardClick: (id: Int) -> Unit
) {
    LazyColumn {

        itemsIndexed(
            items = list,
            key = { _, data -> data.id }
        ) { index, movie ->
            MovieCard(
                position = index,
                movieTitle = movie.title,
                moviePosterUrl = movie.backdropPath,
                movieRating = movie.voteAverage,
                movieGenres = movie.genreNames,
                releaseDate = movie.releaseDate,
                voteCount = movie.voteCount,
                onMovieCardClick = {
                    onMovieCardClick(movie.id)
                }
            )
        }
    }
}


@Composable
private fun HandleEvents(
    events: Flow<FavoriteEvent>,
    navigateToMovieDetail: (Int) -> Unit
) {
    events.collectWithLifecycle {
        when (it) {
            is FavoriteEvent.NavigateToMovieDetail -> navigateToMovieDetail(it.id)
        }
    }
}