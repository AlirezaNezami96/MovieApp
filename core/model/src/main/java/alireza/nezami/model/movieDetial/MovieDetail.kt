package alireza.nezami.model.movieDetial

import alireza.nezami.model.genre.Genre
import alireza.nezami.model.movie.Movie
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    val adult: Boolean = false,
    val backdropPath: String = "",
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Int = 0,
    val genres: List<Genre> = listOf(),
    val homepage: String = "",
    val id: Int = 0,
    val imdbId: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<ProductionCompany> = listOf(),
    val productionCountries: List<ProductionCountry> = listOf(),
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val voteCount: Int = 0
) : Parcelable


/**
 * Extension function to map a [MovieDetail] object into a [Movie] object.
 */
fun MovieDetail.toMovie(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreNames = genres.map { it.name },
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}