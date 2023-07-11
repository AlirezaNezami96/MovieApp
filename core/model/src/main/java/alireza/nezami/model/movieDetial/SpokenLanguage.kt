package alireza.nezami.model.movieDetial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SpokenLanguage(
    val englishName: String,
    val iso6391: String,
    val name: String
) : Parcelable