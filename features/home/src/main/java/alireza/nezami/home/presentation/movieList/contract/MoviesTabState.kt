package alireza.nezami.home.presentation.movieList.contract

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class MoviesTabState(val index: Int) : Parcelable {
    @Parcelize
    object NowPlaying : MoviesTabState(0)

    @Parcelize
    object Upcoming : MoviesTabState(1)

    @Parcelize
    object TopRated : MoviesTabState(2)

    @Parcelize
    object Popular : MoviesTabState(3)
}