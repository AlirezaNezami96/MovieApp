package alireza.nezami.detail.presentation.contract

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class DetailTabState(val index: Int) : Parcelable {
    @Parcelize
    object Overview : DetailTabState(0)

    @Parcelize
    object Info : DetailTabState(1)

    @Parcelize
    object Production : DetailTabState(2)
}