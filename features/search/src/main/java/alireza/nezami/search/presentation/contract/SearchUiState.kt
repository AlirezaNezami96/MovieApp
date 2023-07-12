package alireza.nezami.search.presentation.contract

import alireza.nezami.model.movie.ListState
import alireza.nezami.model.movie.Movie
import alireza.nezami.model.movie.MovieState
import alireza.nezami.model.search.Search
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class SearchUiState(
    val searchQuery: String = "",
    val searchResultList: List<Movie> = emptyList(),
    val searchResultState: MovieState = MovieState(state = ListState.IDLE)
) : Parcelable {

    sealed class PartialState {
        data class AddSearchResult(val movies: Search) : PartialState()
        data class EnterSearchQuery(val query: String) : PartialState()
        data class SearchResultError(val message: String) : PartialState()
        data class SearchResultLoading(val show: Boolean) : PartialState()
    }
}
