package alireza.nezami.designsystem.component

import alireza.nezami.designsystem.R
import alireza.nezami.designsystem.theme.MovieAppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
    movieRating: Float,
    movieGenres: List<String>,
    releaseDate: String,
    duration: String
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
            .padding(8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors()
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .background(brush = Brush.verticalGradient(gradientColors))
                .padding(all = 16.dp)
        ) {
            Box(modifier = Modifier) {
                DynamicAsyncImage(
                    contentDescription = "Movie Poster",
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.medium),
                    imageUrl = moviePosterUrl
                )

                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background.copy(
                                alpha = 0.5f
                            ),
                            shape = MaterialTheme.shapes.small
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating Icon",
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .size(16.dp)
                            .padding(end = 4.dp)
                    )
                    Text(
                        text = movieRating.toString(),
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            Text(
                text = movieTitle,
                style = MaterialTheme.typography.headlineSmall.copy(
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
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = movieGenres.joinToString(", "),
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.padding(start = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = "Release Date Icon",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = releaseDate,
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                    modifier = Modifier.padding(start = 4.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_duration),
                    contentDescription = "Duration Icon",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = duration,
                    style = MaterialTheme.typography.titleSmall.copy(
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

@Preview
@Composable
fun MovieCardPreview() {
    MovieAppTheme {
        MovieCard(
            position = 0,
            movieTitle = "Spiderman",
            moviePosterUrl = "https://image.tmdb.org/t/p/original/fiVW06jE7z9YnO4trhaMEdclSiC.jpg",
            movieRating = 3.7f,
            movieGenres = listOf("Action", "Drama"),
            releaseDate = "2019",
            duration = "134 Minutes"
        )
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
            movieRating = 3.7f,
            movieGenres = listOf("Action", "Drama"),
            releaseDate = "2019",
            duration = "134 Minutes"
        )
    }
}
