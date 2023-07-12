package alireza.nezami.network.model.genre


import alireza.nezami.common.extensions.orZero
import alireza.nezami.model.genre.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreResponse(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null
)

fun GenreResponse?.asExternalModel() = Genre(
    id = this?.id.orZero(),
    name = this?.name.orEmpty()
)