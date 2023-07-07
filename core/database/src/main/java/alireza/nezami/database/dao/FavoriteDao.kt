package alireza.nezami.database.dao

import alireza.nezami.database.entity.movie.MovieEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [MovieEntity] access
 */
@Dao
interface FavoriteDao {

    /**
     * Fetches movie list that match the query parameters
     */
    @Transaction
    @Query(value = "SELECT * FROM favorites")
    fun getMovieDetail(): Flow<List<MovieEntity>>

    /**
     * Inserts [entity] into the db if they don't exist, and replaces those that do
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceMovieToFavorite(entity: MovieEntity)


    /**
     * Deletes row in the db matching the specified [id]
     */
    @Query(
        value = """
            DELETE FROM favorites
            WHERE id is (:id)
        """,
    )
    suspend fun deleteFavorite(id: Int)
}
