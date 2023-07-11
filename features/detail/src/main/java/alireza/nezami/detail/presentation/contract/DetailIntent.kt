package alireza.nezami.detail.presentation.contract

sealed class DetailIntent {
    data class GetMovieDetail(val id: Int) : DetailIntent()
    object OnFavoriteClick : DetailIntent()
    object OnNavigateBackClick : DetailIntent()
    data class ChangeTab(val selectedTabIndex: Int) : DetailIntent()
}
