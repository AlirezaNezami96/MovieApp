package alireza.nezami.database.di

import alireza.nezami.database.MovieAppDatabase
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
    ): MovieAppDatabase = Room.databaseBuilder(
        context,
        MovieAppDatabase::class.java,
        "movie-app-database",
    ).build()
}
