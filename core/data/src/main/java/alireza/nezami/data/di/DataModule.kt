package alireza.nezami.data.di

import alireza.nezami.data.repository.FavoritesRepository
import alireza.nezami.data.repository.GenreRepository
import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.data.repository.MovieRepositoryImpl
import alireza.nezami.data.repository.OfflineFavoriteRepository
import alireza.nezami.data.repository.OfflineFirstGenreRepository
import alireza.nezami.data.repository.SearchRepository
import alireza.nezami.data.repository.SearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module responsible for providing data dependencies.
 * Specifies the bindings for repository interfaces to their respective implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    /**
     * Binds the [OfflineFirstGenreRepository] implementation to the [GenreRepository] interface.
     *
     * @param repository The [OfflineFirstGenreRepository] instance.
     * @return The [GenreRepository] implementation.
     */
    @Binds
    fun bindsGenreRepository(
        repository: OfflineFirstGenreRepository,
    ): GenreRepository

    /**
     * Binds the [MovieRepositoryImpl] implementation to the [MovieRepository] interface.
     *
     * @param repository The [MovieRepositoryImpl] instance.
     * @return The [MovieRepository] implementation.
     */
    @Binds
    fun bindsMovieRepository(
        repository: MovieRepositoryImpl,
    ): MovieRepository

    /**
     * Binds the [SearchRepositoryImpl] implementation to the [SearchRepository] interface.
     *
     * @param repository The [SearchRepositoryImpl] instance.
     * @return The [SearchRepository] implementation.
     */
    @Binds
    fun bindsSearchRepository(
        repository: SearchRepositoryImpl,
    ): SearchRepository

    /**
     * Binds the [OfflineFavoriteRepository] implementation to the [FavoritesRepository] interface.
     *
     * @param repository The [OfflineFavoriteRepository] instance.
     * @return The [FavoritesRepository] implementation.
     */
    @Binds
    fun bindsSearchRepository(
        repository: OfflineFavoriteRepository,
    ): FavoritesRepository

}
