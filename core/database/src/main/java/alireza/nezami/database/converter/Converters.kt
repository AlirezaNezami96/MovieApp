package alireza.nezami.database.converter

import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.database.entity.movie.BelongsToCollectionEntity
import alireza.nezami.database.entity.movie.ProductionCompanyEntity
import alireza.nezami.database.entity.movie.ProductionCountryEntity
import alireza.nezami.database.entity.movie.SpokenLanguageEntity
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

class ProductionCompanyListTypeConverter {
    @TypeConverter
    fun fromList(productionCompanies: List<ProductionCompanyEntity>): String {
        return Json.encodeToString(productionCompanies)
    }

    @TypeConverter
    fun toList(productionCompanyString: String): List<ProductionCompanyEntity> {
        return Json.decodeFromString(productionCompanyString)
    }
}

class ProductionCountryListTypeConverter {
    @TypeConverter
    fun fromList(productionCountries: List<ProductionCountryEntity>): String {
        return Json.encodeToString(productionCountries)
    }

    @TypeConverter
    fun toList(productionCountryString: String): List<ProductionCountryEntity> {
        return Json.decodeFromString(productionCountryString)
    }
}

class SpokenLanguageListTypeConverter {
    @TypeConverter
    fun fromList(spokenLanguages: List<SpokenLanguageEntity>): String {
        return Json.encodeToString(spokenLanguages)
    }

    @TypeConverter
    fun toList(spokenLanguageString: String): List<SpokenLanguageEntity> {
        return Json.decodeFromString(spokenLanguageString)
    }
}

class BelongsToCollectionTypeConverter {
    @TypeConverter
    fun fromBelongsToCollection(collection: BelongsToCollectionEntity): String {
        return "${collection.id},${collection.name},${collection.posterPath},${collection.backdropPath}"
    }

    @TypeConverter
    fun toBelongsToCollection(collectionString: String): BelongsToCollectionEntity {
        val parts = collectionString.split(",")
        return BelongsToCollectionEntity(
            id = parts[0].toInt(),
            name = parts[1],
            posterPath = parts[2],
            backdropPath = parts[3]
        )
    }
}

