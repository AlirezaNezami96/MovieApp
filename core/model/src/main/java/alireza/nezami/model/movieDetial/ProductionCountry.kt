package alireza.nezami.model.movieDetial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountry(
    val iso31661: String,
    val name: String
) : Parcelable