package alireza.nezami.network.model.movie


import alireza.nezami.common.extensions.orZero
import alireza.nezami.model.movie.Dates
import alireza.nezami.model.movie.Movies
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMovieResponseDto(
    @SerialName("dates")
    val dates: DatesDto? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<MovieResultDto?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)

fun GetMovieResponseDto?.asExternalModel(): Movies {
    return Movies(
        dates = this?.dates.asExternalModel(),
        page = this?.page.orZero(),
        results = this?.results?.mapNotNull { it?.asExternalModel() } ?: emptyList(),
        totalPages = this?.totalPages.orZero(),
        totalResults = this?.totalResults.orZero()
    )
}
