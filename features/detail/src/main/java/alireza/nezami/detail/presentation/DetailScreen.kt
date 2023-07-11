package alireza.nezami.detail.presentation

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.component.DynamicAsyncImage
import alireza.nezami.designsystem.component.HeightSpacer
import alireza.nezami.designsystem.component.Rating
import alireza.nezami.designsystem.component.Tab
import alireza.nezami.designsystem.component.ScrollableTabRow
import alireza.nezami.designsystem.component.TabRow
import alireza.nezami.designsystem.component.TopAppBar
import alireza.nezami.designsystem.component.WidthSpacer
import alireza.nezami.detail.presentation.contract.DetailIntent
import alireza.nezami.detail.presentation.contract.DetailTabState
import alireza.nezami.detail.presentation.contract.DetailUiState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    DetailContent(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun DetailContent(uiState: DetailUiState, onIntent: (DetailIntent) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        topBar(uiState, onIntent)
        loading(uiState.isLoading)
        header(uiState, onIntent)
        briefInfo(uiState)
        tabContent(uiState, onIntent)
        overViewContent(
            shown = uiState.selectedTabIndex == DetailTabState.Overview.index,
            overview = uiState.movieDetail?.overview.orEmpty()
        )
    }

}

fun LazyListScope.overViewContent(shown: Boolean, overview: String) {
    item {
        HeightSpacer(value = 16)
        if (shown) {
            Text(
                text = overview,
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

fun LazyListScope.briefInfo(uiState: DetailUiState) {
    item {
        HeightSpacer(value = 8)
    }

    item {
        uiState.movieDetail?.let { movieDetail ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                RowInfo(
                    icon = R.drawable.ic_calendar,
                    text = movieDetail.releaseDate
                )
                RowInfo(
                    icon = R.drawable.ic_revenue,
                    text = movieDetail.revenue.toString()
                )
                RowInfo(
                    icon = R.drawable.ic_genre,
                    text = movieDetail.genres.first().name
                )
            }
        }
    }

    item {
        HeightSpacer(value = 8)
    }
}

@Composable
fun RowInfo(
    icon: Int,
    text: String
) {
    Row(
        modifier = Modifier
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Release Date Icon",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.padding(start = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        WidthSpacer(value = 16)
    }
}

fun LazyListScope.header(uiState: DetailUiState, onIntent: (DetailIntent) -> Unit) {
    item {
        uiState.movieDetail?.let { movieDetail ->

            ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                val (poster, backDrop, title, vote) = createRefs()

                DynamicAsyncImage(
                    contentDescription = "Movie BackDrop",
                    modifier = Modifier
                        .constrainAs(backDrop) {
                            top.linkTo(parent.top)
                        }
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(
                            shape = RoundedCornerShape(
                                bottomStart = 16.dp,
                                bottomEnd = 16.dp
                            )
                        ),
                    imageUrl = movieDetail.backdropPath
                )

                DynamicAsyncImage(
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .constrainAs(poster) {
                            bottom.linkTo(backDrop.bottom)
                            top.linkTo(backDrop.bottom)
                            start.linkTo(parent.start, 24.dp)
                        }
                        .height(120.dp)
                        .aspectRatio(0.7f)
                        .clip(shape = MaterialTheme.shapes.medium),
                    imageUrl = movieDetail.posterPath
                )


                Rating(
                    modifier = Modifier.constrainAs(vote) {
                        bottom.linkTo(backDrop.bottom, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                    },
                    rating = movieDetail.voteAverage
                )

                Text(
                    text = movieDetail.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .constrainAs(title) {
                            top.linkTo(backDrop.bottom, 8.dp)
                            start.linkTo(poster.end, 16.dp)
                        }
                        .fillMaxWidth(),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.topBar(uiState: DetailUiState, onIntent: (DetailIntent) -> Unit) {
    item {
        TopAppBar(
            titleRes = R.string.detail,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = Icons.Rounded.KeyboardArrowLeft,
            onNavigationClick = {
                onIntent.invoke(DetailIntent.OnNavigateBackClick)
            },
            actionIcon = if (uiState.isFavorite) Icons.Outlined.Favorite
            else Icons.Outlined.FavoriteBorder,
            onActionClick = {
                onIntent.invoke(DetailIntent.OnFavoriteClick)
            }
        )
    }
}

fun LazyListScope.loading(loading: Boolean) {
    item {
        if (loading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}


private fun LazyListScope.tabContent(uiState: DetailUiState, onIntent: (DetailIntent) -> Unit) {
    item {
        HeightSpacer(value = 8)
    }

    item {
        val titles = listOf(
            R.string.overview,
            R.string.info,
            R.string.production
        )

        TabRow(
            selectedTabIndex = uiState.selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = uiState.selectedTabIndex == index,
                    onClick = { onIntent.invoke(DetailIntent.ChangeTab(index)) },
                    text = {
                        Text(text = stringResource(title))
                    },
                )
            }
        }
    }
}

