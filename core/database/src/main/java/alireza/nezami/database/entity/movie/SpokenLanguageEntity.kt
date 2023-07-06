package alireza.nezami.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spoken_language")
data class SpokenLanguageEntity(
    @PrimaryKey
    val iso6391: String,
    val englishName: String,
    val name: String
)
