package alireza.nezami.home.presentation.movieList.contract

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class MoviesTabState : Parcelable {
    @Parcelize
    object NowPlaying : MoviesTabState()

    @Parcelize
    object Upcoming : MoviesTabState()

    @Parcelize
    object TopRated : MoviesTabState()

    @Parcelize
    object Popular : MoviesTabState()
}