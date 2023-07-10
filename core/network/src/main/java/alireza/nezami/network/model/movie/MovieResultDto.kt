package alireza.nezami.network.model.movie


import alireza.nezami.common.extensions.formatToOneDecimalPlace
import alireza.nezami.common.extensions.orFalse
import alireza.nezami.common.extensions.orZero
import alireza.nezami.common.utils.DateUtils
import alireza.nezami.model.movie.Movie
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResultDto(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("original_language")
    val originalLanguage: String? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("overview")
    val overview: String? = null,
    @SerialName("popularity")
    val popularity: Double? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: Boolean? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null,

    var genreNames: List<String> = emptyList(),
)

fun MovieResultDto?.asExternalModel(): Movie {
    return Movie(
        adult = this?.adult.orFalse(),
        backdropPath = "https://image.tmdb.org/t/p/original/${this?.backdropPath.orEmpty()}",
        id = this?.id.orZero(),
        originalLanguage = this?.originalLanguage.orEmpty(),
        originalTitle = this?.originalTitle.orEmpty(),
        overview = this?.overview.orEmpty(),
        popularity = this?.popularity.orZero(),
        posterPath = "https://image.tmdb.org/t/p/original/${this?.posterPath.orEmpty()}",
        releaseDate = DateUtils.getYearFromDate(this?.releaseDate.orEmpty()),
        title = this?.title.orEmpty(),
        video = this?.video.orFalse(),
        voteAverage = this?.voteAverage.formatToOneDecimalPlace().orZero(),
        voteCount = this?.voteCount.orZero()
    )
}

