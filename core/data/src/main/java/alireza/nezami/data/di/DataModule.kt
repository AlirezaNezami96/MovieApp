package alireza.nezami.data.di

import alireza.nezami.data.repository.GenreRepository
import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.data.repository.MovieRepositoryImpl
import alireza.nezami.data.repository.OfflineFavoriteRepository
import alireza.nezami.data.repository.SearchRepository
import alireza.nezami.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsGenreRepository(
        topicsRepository: OfflineFavoriteRepository,
    ): GenreRepository

    @Binds
    fun bindsMovieRepository(
        topicsRepository: MovieRepositoryImpl,
    ): MovieRepository

    @Binds
    fun bindsSearchRepository(
        topicsRepository: SearchRepositoryImpl,
    ): SearchRepository

}
