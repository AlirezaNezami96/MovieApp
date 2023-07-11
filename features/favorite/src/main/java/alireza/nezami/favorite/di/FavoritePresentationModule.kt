package alireza.nezami.favorite.di

import alireza.nezami.favorite.presentation.contract.FavoriteUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object FavoritePresentationModule {

    @Provides
    fun provideFavoriteUiState(): FavoriteUiState = FavoriteUiState()

}