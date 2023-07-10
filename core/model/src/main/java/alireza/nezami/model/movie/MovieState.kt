package alireza.nezami.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieState(
    val state: ListState,
    val page: Int
) : Parcelable {
    companion object {
        val DEFAULT = MovieState(
            state = ListState.LOADING,
            page = 1
        )
    }
}

enum class ListState {
    IDLE,
    LOADING,
    PAGINATING,
    ERROR,
    PAGINATION_EXHAUST,
}
