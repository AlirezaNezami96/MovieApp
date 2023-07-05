package alireza.nezami.network.model.genre


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGenreListResponseDto(
    @SerialName("genres")
    val genres: List<Genre?>? = null
)