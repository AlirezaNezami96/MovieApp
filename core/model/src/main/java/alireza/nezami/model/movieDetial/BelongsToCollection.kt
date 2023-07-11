package alireza.nezami.model.movieDetial

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BelongsToCollection(
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String
) : Parcelable