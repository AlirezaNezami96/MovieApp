package alireza.nezami.network.model.movieDetial


import alireza.nezami.model.movieDetial.ProductionCountry
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String? = null,
    @SerialName("name")
    val name: String? = null
)

fun ProductionCountryDto?.asExternalModel(): ProductionCountry {
    return ProductionCountry(
        iso31661 = this?.iso31661.orEmpty(),
        name = this?.name.orEmpty()
    )
}
