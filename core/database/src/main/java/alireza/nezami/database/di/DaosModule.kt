package alireza.nezami.database.di

import alireza.nezami.database.MovieAppDatabase
import alireza.nezami.database.dao.FavoriteDao
import alireza.nezami.database.dao.GenreDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {
    @Provides
    fun providesFavoriteDao(
        database: MovieAppDatabase,
    ): FavoriteDao = database.favoriteDao()

    @Provides
    fun providesGenreDao(
        database: MovieAppDatabase,
    ): GenreDao = database.genreDao()

}
