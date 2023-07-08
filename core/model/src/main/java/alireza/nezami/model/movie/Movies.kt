package alireza.nezami.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
) : Parcelable {
    companion object {
        val EMPTY = Movies(
            Dates.EMPTY,
            0,
            emptyList(),
            0,
            0
        )
    }
}