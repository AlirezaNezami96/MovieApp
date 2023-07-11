package alireza.nezami.detail.presentation.contract

import alireza.nezami.model.movieDetial.MovieDetail
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class DetailUiState(
    val movieDetail: MovieDetail? = null,
    val selectedTabIndex: Int = DetailTabState.Info.index,

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isFavorite: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data class AddMovieDetail(val detail: MovieDetail) : PartialState()
        data class Error(val errorMessage: String) : PartialState()
        data class Loading(val shown: Boolean) : PartialState()
        data class MakeFavorite(val isFavorite: Boolean) : PartialState()
    }
}
