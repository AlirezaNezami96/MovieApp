package alireza.nezami.network.model.movieDetial


import alireza.nezami.model.movieDetial.SpokenLanguage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageDto(
    @SerialName("english_name")
    val englishName: String? = null,
    @SerialName("iso_639_1")
    val iso6391: String? = null,
    @SerialName("name")
    val name: String? = null
)

fun SpokenLanguageDto?.asExternalModel(): SpokenLanguage {
    return SpokenLanguage(
        englishName = this?.englishName.orEmpty(),
        iso6391 = this?.iso6391.orEmpty(),
        name = this?.name.orEmpty()
    )
}
