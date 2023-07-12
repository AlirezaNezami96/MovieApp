package alireza.nezami.database

import alireza.nezami.database.dao.FavoriteDao
import alireza.nezami.database.dao.GenreDao
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.database.entity.movie.MovieEntity
import alireza.nezami.database.util.GenreIdsTypeConverter
import alireza.nezami.database.util.GenreListTypeConverter
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        GenreEntity::class,
        MovieEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    GenreListTypeConverter::class,
    GenreIdsTypeConverter::class
)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun genreDao(): GenreDao
}
