package alireza.nezami.database.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "belongs_to_collection")
data class BelongsToCollectionEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val posterPath: String,
    val backdropPath: String
)
