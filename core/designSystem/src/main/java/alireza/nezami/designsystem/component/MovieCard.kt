package alireza.nezami.designsystem.component

import alireza.nezami.common.extensions.formatWithCommas
import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.theme.MovieAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MovieCard(
    modifier: Modifier = Modifier,
    position: Int,
    movieTitle: String,
    moviePosterUrl: String,
    movieRating: Double,
    movieGenres: List<String>,
    releaseDate: String,
    voteCount: Int,
    onMovieCardClick: () -> Unit
) {
    val isOddPosition = position % 2 != 0
    val gradientColors =
        if (isOddPosition) listOf(
            Color(0xFF34495E),
            Color(0xFF2C3E50)
        )
        else listOf(
            Color(0xFF54298E),
            Color(0xFF240046)
        )

    Card(
        modifier = modifier
            .padding(
                start = if (isOddPosition) 4.dp else 0.dp,
                end = if (!isOddPosition) 4.dp else 0.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
            .clickable {
                onMovieCardClick()
            },
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors()
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .background(brush = Brush.verticalGradient(gradientColors))
                .padding(all = 8.dp)
        ) {
            Box(modifier = Modifier) {
                DynamicAsyncImage(
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.medium),
                    imageUrl = moviePosterUrl
                )

                Rating(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    rating = movieRating
                )
            }

            Text(
                text = movieTitle,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_genre),
                    contentDescription = "Genre Icon",
                    tint = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = movieGenres.joinToString(", "),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.padding(start = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Release Date Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = releaseDate,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                        modifier = Modifier.padding(start = 4.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Duration Icon",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = voteCount.formatWithCommas(),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        modifier = Modifier.padding(start = 4.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieCardPreview() {
    MovieAppTheme {
        MovieCard(
            position = 0,
            movieTitle = "Spiderman",
            moviePosterUrl = "https://image.tmdb.org/t/p/original/fiVW06jE7z9YnO4trhaMEdclSiC.jpg",
            movieRating = 3.7,
            movieGenres = listOf("Action", "Drama"),
            releaseDate = "2019",
            voteCount = 100
        ) {}
    }
}

@Preview
@Composable
fun MovieCardPreview2() {
    MovieAppTheme {
        MovieCard(
            position = 1,
            movieTitle = "Spiderman",
            moviePosterUrl = "https://image.tmdb.org/t/p/original/fiVW06jE7z9YnO4trhaMEdclSiC.jpg",
            movieRating = 3.7,
            movieGenres = listOf("Action", "Drama"),
            releaseDate = "2019",
            voteCount = 100
        ) {}
    }
}
