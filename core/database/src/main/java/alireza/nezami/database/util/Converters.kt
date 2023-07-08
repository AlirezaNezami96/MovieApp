package alireza.nezami.database.util

import alireza.nezami.database.entity.genre.GenreEntity
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenreListTypeConverter {
    @TypeConverter
    fun fromList(genres: List<GenreEntity>): String {
        return genres.joinToString { it.id.toString() }
    }

    @TypeConverter
    fun toList(genreString: String): List<GenreEntity> {
        val genreIds = genreString.split(",").mapNotNull { it.toIntOrNull() }
        return genreIds.map { GenreEntity("", it) }
    }
}

class GenreIdsTypeConverter {
    @TypeConverter
    fun fromGenreIds(genreIds: List<String>): String {
        return genreIds.joinToString(",")
    }

    @TypeConverter
    fun toGenreIds(genreIdsString: String): List<String> {
        return genreIdsString.split(",")
    }
}


