package alireza.nezami.network.model.search


import alireza.nezami.common.extensions.orZero
import alireza.nezami.model.search.Search
import alireza.nezami.network.model.movie.MovieResultDto
import alireza.nezami.network.model.movie.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchMovieResponseDto(
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<MovieResultDto?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)

fun SearchMovieResponseDto?.asExternalModel(): Search {
    return Search(
        page = this?.page.orZero(),
        results = this?.results?.mapNotNull { it?.asExternalModel() } ?: emptyList(),
        totalPages = this?.totalPages.orZero(),
        totalResults = this?.totalResults.orZero()
    )
}
