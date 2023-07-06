/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package alireza.nezami.database.dao

import alireza.nezami.database.entity.movie.MovieDetailEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [MovieDetailEntity] access
 */
@Dao
interface FavoriteDao {

    /**
     * Fetches movie detail that match the query parameters
     */
    @Transaction
    @Query(value = "SELECT * FROM favorite ORDER BY favoriteAddTime DESC")
    fun getMovieDetail(): Flow<MovieDetailEntity>

    /**
     * Inserts [entity] into the db if they don't exist, and replaces those that do
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplaceMovieToFavorite(entity: MovieDetailEntity)


    /**
     * Deletes row in the db matching the specified [id]
     */
    @Query(
        value = """
            DELETE FROM favorite
            WHERE id is (:id)
        """,
    )
    suspend fun deleteFavorite(id: Int)
}
