package alireza.nezami.data.di

import alireza.nezami.data.repository.FavoritesRepository
import alireza.nezami.data.repository.GenreRepository
import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.data.repository.MovieRepositoryImpl
import alireza.nezami.data.repository.OfflineFavoriteRepository
import alireza.nezami.data.repository.OfflineFirstGenreRepository
import alireza.nezami.data.repository.SearchRepository
import alireza.nezami.data.repository.SearchRepositoryImpl
import alireza.nezami.data.util.ConnectivityManagerNetworkMonitor
import alireza.nezami.data.util.NetworkMonitor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module responsible for providing data dependencies.
 * Specifies the bindings for repository interfaces to their respective implementations.
 */
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * Provides the [GenreRepository] implementation by using the [OfflineFirstGenreRepository].
     *
     * @param repository The [OfflineFirstGenreRepository] instance.
     * @return The [GenreRepository] implementation.
     */
    @Provides
    fun provideGenreRepository(repository: OfflineFirstGenreRepository): GenreRepository {
        return repository
    }

    /**
     * Provides the [MovieRepository] implementation by using the [MovieRepositoryImpl].
     *
     * @param repository The [MovieRepositoryImpl] instance.
     * @return The [MovieRepository] implementation.
     */
    @Provides
    fun provideMovieRepository(repository: MovieRepositoryImpl): MovieRepository {
        return repository
    }

    /**
     * Provides the [SearchRepository] implementation by using the [SearchRepositoryImpl].
     *
     * @param repository The [SearchRepositoryImpl] instance.
     * @return The [SearchRepository] implementation.
     */
    @Provides
    fun provideSearchRepository(repository: SearchRepositoryImpl): SearchRepository {
        return repository
    }

    /**
     * Provides the [FavoritesRepository] implementation by using the [OfflineFavoriteRepository].
     *
     * @param repository The [OfflineFavoriteRepository] instance.
     * @return The [FavoritesRepository] implementation.
     */
    @Provides
    fun provideFavoriteRepository(repository: OfflineFavoriteRepository): FavoritesRepository {
        return repository
    }

    /**
     * Provides the [NetworkMonitor] implementation by using the [ConnectivityManagerNetworkMonitor].
     *
     * @param networkMonitor The [ConnectivityManagerNetworkMonitor] instance.
     * @return The [NetworkMonitor] implementation.
     */
    @Provides
    fun provideNetworkMonitor(networkMonitor: ConnectivityManagerNetworkMonitor): NetworkMonitor {
        return networkMonitor
    }
}
