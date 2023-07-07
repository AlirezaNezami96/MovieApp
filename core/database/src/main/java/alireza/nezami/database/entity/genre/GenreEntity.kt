package alireza.nezami.database.entity.genre

import alireza.nezami.model.genre.Genre
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(

    @ColumnInfo(name = "name")
    val name: String,

    @PrimaryKey
    val id: Int
)

fun GenreEntity.asExternalModel() = Genre(
    id = id,
    name = name
)
