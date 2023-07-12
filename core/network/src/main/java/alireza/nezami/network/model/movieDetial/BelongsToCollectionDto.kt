package alireza.nezami.network.model.movieDetial


import alireza.nezami.common.extensions.orZero
import alireza.nezami.model.movieDetial.BelongsToCollection
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null
)

fun BelongsToCollectionDto?.asExternalModel(): BelongsToCollection {
    return BelongsToCollection(
        id = this?.id.orZero(),
        name = this?.name.orEmpty(),
        posterPath = this?.posterPath.orEmpty(),
        backdropPath = this?.backdropPath.orEmpty()
    )
}
