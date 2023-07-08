package alireza.nezami.home.di

import alireza.nezami.home.presentation.movieList.contract.MoviesUiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object MoviesPresentationModule {

    @Provides
    fun provideMoviesUiState(): MoviesUiState = MoviesUiState()

}