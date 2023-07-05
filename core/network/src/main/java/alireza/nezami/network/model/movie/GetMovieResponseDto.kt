package alireza.nezami.network.model.movie


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetMovieResponseDto(
    @SerialName("dates")
    val dates: Dates? = null,
    @SerialName("page")
    val page: Int? = null,
    @SerialName("results")
    val results: List<MovieResult?>? = null,
    @SerialName("total_pages")
    val totalPages: Int? = null,
    @SerialName("total_results")
    val totalResults: Int? = null
)