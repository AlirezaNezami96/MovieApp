package alireza.nezami.network.model.movieDetial


import alireza.nezami.common.extensions.orZero
import alireza.nezami.model.movieDetial.ProductionCompany
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("logo_path")
    val logoPath: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("origin_country")
    val originCountry: String? = null
)

fun ProductionCompanyDto?.asExternalModel(): ProductionCompany {
    return ProductionCompany(
        id = this?.id.orZero(),
        logoPath = this?.logoPath.orEmpty(),
        name = this?.name.orEmpty(),
        originCountry = this?.originCountry.orEmpty()
    )
}
