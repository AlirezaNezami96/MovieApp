package alireza.nezami.database.entity.movie

import alireza.nezami.database.converter.GenreListTypeConverter
import alireza.nezami.database.converter.ProductionCompanyListTypeConverter
import alireza.nezami.database.converter.ProductionCountryListTypeConverter
import alireza.nezami.database.converter.SpokenLanguageListTypeConverter
import alireza.nezami.model.genre.Genre
import alireza.nezami.model.movieDetial.ProductionCompany
import alireza.nezami.model.movieDetial.ProductionCountry
import alireza.nezami.model.movieDetial.SpokenLanguage
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "favorite")
data class MovieDetailEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollectionEntity,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompanyEntity>,
    val productionCountries: List<ProductionCountryEntity>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguageEntity>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val favoriteAddTime: String
) {
    @TypeConverters(GenreListTypeConverter::class)
    lateinit var genresTypeConverter: List<Genre>

    @TypeConverters(ProductionCompanyListTypeConverter::class)
    lateinit var productionCompaniesTypeConverter: List<ProductionCompany>

    @TypeConverters(ProductionCountryListTypeConverter::class)
    lateinit var productionCountriesTypeConverter: List<ProductionCountry>

    @TypeConverters(SpokenLanguageListTypeConverter::class)
    lateinit var spokenLanguagesTypeConverter: List<SpokenLanguage>
}
