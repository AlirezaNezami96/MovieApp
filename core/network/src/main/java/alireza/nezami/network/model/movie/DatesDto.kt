package alireza.nezami.network.model.movie


import alireza.nezami.model.movie.Dates
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DatesDto(
    @SerialName("maximum")
    val maximum: String? = null,
    @SerialName("minimum")
    val minimum: String? = null
)

fun DatesDto?.asExternalModel(): Dates {
    return Dates(
        maximum = this?.maximum.orEmpty(),
        minimum = this?.minimum.orEmpty()
    )
}
