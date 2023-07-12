package alireza.nezami.detail.presentation

import alireza.nezami.common.extensions.orEmptyList
import alireza.nezami.common.extensions.toYesOrNo
import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.component.DynamicAsyncImage
import alireza.nezami.designsystem.component.HeightSpacer
import alireza.nezami.designsystem.component.Rating
import alireza.nezami.designsystem.component.Tab
import alireza.nezami.designsystem.component.TabRow
import alireza.nezami.designsystem.component.TopAppBar
import alireza.nezami.designsystem.component.WidthSpacer
import alireza.nezami.designsystem.extensions.collectWithLifecycle
import alireza.nezami.detail.presentation.contract.DetailEvent
import alireza.nezami.detail.presentation.contract.DetailIntent
import alireza.nezami.detail.presentation.contract.DetailTabState
import alireza.nezami.detail.presentation.contract.DetailUiState
import alireza.nezami.model.movieDetial.MovieDetail
import alireza.nezami.model.movieDetial.ProductionCompany
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import kotlinx.coroutines.flow.Flow

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    HandleEvents(
        events = viewModel.event,
        navigateUp = navigateUp
    )

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
        infoContent(
            shown = uiState.selectedTabIndex == DetailTabState.Info.index,
            movie = uiState.movieDetail
        )
        productionContent(
            shown = uiState.selectedTabIndex == DetailTabState.Production.index,
            productionList = uiState.movieDetail?.productionCompanies.orEmptyList()
        )
    }

}

fun LazyListScope.infoContent(shown: Boolean, movie: MovieDetail?) {
    if (shown) {
        movie?.let { movieDetail ->
            item {
                InfoItem(
                    title = stringResource(R.string.genres),
                    icon = R.drawable.ic_genre,
                    text = movieDetail.genres.joinToString(",") { it.name }
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.budget),
                    icon = R.drawable.ic_revenue,
                    text = movieDetail.budget.toString()
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.release_date),
                    icon = R.drawable.ic_calendar,
                    text = movieDetail.releaseDate
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.status),
                    icon = R.drawable.ic_status,
                    text = movieDetail.status
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.revenue),
                    icon = R.drawable.ic_revenue,
                    text = movieDetail.revenue.toString()
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.vote_average),
                    icon = R.drawable.ic_user,
                    text = movieDetail.voteAverage.toString()
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.vote_count),
                    icon = R.drawable.ic_status,
                    text = movieDetail.voteCount.toString()
                )
            }
            item {
                InfoItem(
                    title = stringResource(R.string.adult),
                    icon = R.drawable.ic_duration,
                    text = movieDetail.adult.toYesOrNo()
                )
            }
        }
    }
}

@Composable
fun InfoItem(
    icon: Int,
    title: String,
    text: String,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(20.dp)
        )

        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(0.4f)
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
            ),
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(0.7f)
        )

    }

    Divider(
        modifier = Modifier
            .padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.onSurface,
        thickness = (0.5).dp
    )
}

fun LazyListScope.productionContent(shown: Boolean, productionList: List<ProductionCompany>) {
    if (shown) {
        items(productionList) { production ->
            ProductionItem(production)
            HeightSpacer(value = 8)
            Divider(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}

@Composable
fun ProductionItem(production: ProductionCompany) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        DynamicAsyncImage(
            contentDescription = "Production company",
            modifier = Modifier
                .size(36.dp)
                .clip(
                    shape = MaterialTheme.shapes.small
                ),
            imageUrl = production.logoPath
        )

        WidthSpacer(value = 16)

        Text(
            text = production.name,
            style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
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
                    fontWeight = FontWeight.Normal
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
                    text = movieDetail.genres.firstOrNull()?.name ?: ""
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

fun LazyListScope.header(uiState: DetailUiState, onIntent: (DetailIntent) -> Unit) {
    item {
        uiState.movieDetail?.let { movieDetail ->

            ConstraintLayout(modifier = Modifier) {
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
                        .wrapContentWidth(),
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
            actionIconTint = if (uiState.isFavorite) MaterialTheme.colorScheme.secondary
            else null,
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


@Composable
private fun HandleEvents(
    events: Flow<DetailEvent>,
    navigateUp: () -> Unit
) {
    events.collectWithLifecycle {
        when (it) {
            DetailEvent.NavigateBack -> navigateUp()
        }
    }
}


