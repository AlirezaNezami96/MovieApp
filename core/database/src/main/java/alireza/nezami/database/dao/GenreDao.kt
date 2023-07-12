package alireza.nezami.database.dao

import alireza.nezami.database.entity.genre.GenreEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [GenreEntity] access
 */
@Dao
interface GenreDao {

    /**
     * Fetches genre list that match the query parameters
     */
    @Transaction
    @Query(value = "SELECT * FROM genre")
    fun getGenreList(): Flow<List<GenreEntity>>

    /**
     * Inserts [entities] into the db if they don't exist, and replaces those that do
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceGenreList(entities: List<GenreEntity>)
}
