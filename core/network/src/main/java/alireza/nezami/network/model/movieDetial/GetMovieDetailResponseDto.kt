package alireza.nezami.network.model.movieDetial


import alireza.nezami.common.extensions.formatToOneDecimalPlace
import alireza.nezami.common.extensions.orFalse
import alireza.nezami.common.extensions.orZero
import alireza.nezami.common.utils.DateUtils
import alireza.nezami.model.movieDetial.BelongsToCollection
import alireza.nezami.model.movieDetial.MovieDetail
import alireza.nezami.network.model.genre.GenreResponse
import alireza.nezami.network.model.genre.asExternalModel
import alireza.nezami.network.model.movie.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMovieDetailResponseDto(
    @SerialName("adult")
    val adult: Boolean? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollectionDto? = null,
    @SerialName("budget")
    val budget: Int? = null,
    @SerialName("genres")
    val genres: List<GenreResponse?>? = null,
    @SerialName("homepage")
    val homepage: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("imdb_id")
    val imdbId: String? = null,
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
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto?>? = null,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto?>? = null,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("revenue")
    val revenue: Int? = null,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto?>? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("tagline")
    val tagline: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("video")
    val video: Boolean? = null,
    @SerialName("vote_average")
    val voteAverage: Double? = null,
    @SerialName("vote_count")
    val voteCount: Int? = null
)

fun GetMovieDetailResponseDto?.asExternalModel(): MovieDetail {
    return MovieDetail(
        adult = this?.adult.orFalse(),
        backdropPath = "https://image.tmdb.org/t/p/original/${this?.backdropPath.orEmpty()}",
        belongsToCollection = this?.belongsToCollection.asExternalModel(),
        budget = this?.budget.orZero(),
        genres = this?.genres?.mapNotNull { it?.asExternalModel() } ?: emptyList(),
        homepage = this?.homepage.orEmpty(),
        id = this?.id.orZero(),
        imdbId = this?.imdbId.orEmpty(),
        originalLanguage = this?.originalLanguage.orEmpty(),
        originalTitle = this?.originalTitle.orEmpty(),
        overview = this?.overview.orEmpty(),
        popularity = this?.popularity.orZero(),
        posterPath = "https://image.tmdb.org/t/p/w342/${this?.posterPath.orEmpty()}",
        productionCompanies = this?.productionCompanies?.mapNotNull { it?.asExternalModel() } ?: emptyList(),
        productionCountries = this?.productionCountries?.mapNotNull { it?.asExternalModel() } ?: emptyList(),
        releaseDate = DateUtils.getYearFromDate(this?.releaseDate.orEmpty()),
        revenue = this?.revenue.orZero(),
        runtime = this?.runtime.orZero(),
        spokenLanguages = this?.spokenLanguages?.mapNotNull { it?.asExternalModel() } ?: emptyList(),
        status = this?.status.orEmpty(),
        tagline = this?.tagline.orEmpty(),
        title = this?.title.orEmpty(),
        video = this?.video.orFalse(),
        voteAverage = this?.voteAverage.formatToOneDecimalPlace().orZero(),
        voteCount = this?.voteCount.orZero()
    )
}
