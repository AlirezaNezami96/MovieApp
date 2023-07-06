package alireza.nezami.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "production_country")
data class ProductionCountryEntity(
    @PrimaryKey
    val iso31661: String,
    val name: String
)
