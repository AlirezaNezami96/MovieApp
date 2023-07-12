package alireza.nezami.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dates(
    val maximum: String,
    val minimum: String
) : Parcelable {
    companion object {
        val EMPTY = Dates(
            "",
            ""
        )
    }
}