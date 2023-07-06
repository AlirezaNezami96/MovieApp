package alireza.nezami.data.repository

import alireza.nezami.model.movie.Movies
import kotlinx.coroutines.flow.Flow


/**
 * Data layer interface for the search feature.
 */
interface SearchRepository {

    /**
     * Query the movies with the [query] and returns it as a [Flow] of [Movies]
     */
    suspend fun searchForMovies(query: String? = null, page: Int? = null): Flow<Movies>

}