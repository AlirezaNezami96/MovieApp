package alireza.nezami.detail.presentation.contract

sealed class DetailIntent {
    data class GetMovieDetail(val id: Int) : DetailIntent()
    data class OnFavoriteClick(val id: Int) : DetailIntent()
    data class ChangeTab(val selectedTabIndex: Int) : DetailIntent()
}
