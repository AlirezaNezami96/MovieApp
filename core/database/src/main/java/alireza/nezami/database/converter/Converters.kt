package alireza.nezami.database.converter

import alireza.nezami.model.genre.Genre
import alireza.nezami.model.movieDetial.ProductionCompany
import alireza.nezami.model.movieDetial.ProductionCountry
import alireza.nezami.model.movieDetial.SpokenLanguage
import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class GenreListTypeConverter {
    @TypeConverter
    fun fromList(genres: List<Genre>): String {
        return genres.joinToString { it.id.toString() }
    }

    @TypeConverter
    fun toList(genreString: String): List<Genre> {
        val genreIds = genreString.split(",").mapNotNull { it.toIntOrNull() }
        return genreIds.map { Genre(it, "") }
    }
}

class ProductionCompanyListTypeConverter {
    @TypeConverter
    fun fromList(productionCompanies: List<ProductionCompany>): String {
        return Json.encodeToString(productionCompanies)
    }

    @TypeConverter
    fun toList(productionCompanyString: String): List<ProductionCompany> {
        return Json.decodeFromString(productionCompanyString)
    }
}

class ProductionCountryListTypeConverter {
    @TypeConverter
    fun fromList(productionCountries: List<ProductionCountry>): String {
        return Json.encodeToString(productionCountries)
    }

    @TypeConverter
    fun toList(productionCountryString: String): List<ProductionCountry> {
        return Json.decodeFromString(productionCountryString)
    }
}

class SpokenLanguageListTypeConverter {
    @TypeConverter
    fun fromList(spokenLanguages: List<SpokenLanguage>): String {
        return Json.encodeToString(spokenLanguages)
    }

    @TypeConverter
    fun toList(spokenLanguageString: String): List<SpokenLanguage> {
        return Json.decodeFromString(spokenLanguageString)
    }
}
