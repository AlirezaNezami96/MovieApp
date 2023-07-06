package alireza.nezami.data.repository

import alireza.nezami.model.genre.Genre
import kotlinx.coroutines.flow.Flow


/**
 * Data layer interface for the Genre feature.
 */
interface GenreRepository {

    /**
     * Gets the genre list from either LocalDB or Network (Offline first)
     */
    suspend fun getGenreList(): Flow<List<Genre>>

}