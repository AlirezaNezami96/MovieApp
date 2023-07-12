package alireza.nezami.detail.di

import alireza.nezami.detail.presentation.contract.DetailUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DetailPresentationModule {

    @Provides
    fun provideDetailUiState(): DetailUiState = DetailUiState()

}