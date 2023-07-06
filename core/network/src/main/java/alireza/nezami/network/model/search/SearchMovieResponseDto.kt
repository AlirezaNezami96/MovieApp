package alireza.nezami.network.model.search


import alireza.nezami.network.model.movie.MovieResultDto
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