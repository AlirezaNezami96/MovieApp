package alireza.nezami.search.di

import alireza.nezami.search.presentation.contract.SearchUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SearchPresentationModule {

    @Provides
    fun provideSearchUiState(): SearchUiState = SearchUiState()

}