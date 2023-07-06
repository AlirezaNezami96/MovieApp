package alireza.nezami.data.repository

import alireza.nezami.model.movie.Movies
import alireza.nezami.model.movieDetial.MovieDetail
import kotlinx.coroutines.flow.Flow


/**
 * Data layer interface for the movie feature.
 */
interface MovieRepository {

    /*
        Gets the Now Playing Movies
     */
    suspend fun getNowPlaying(page: Int? = null): Flow<Movies>

    /*
        Gets the Popular Movies
     */
    suspend fun getPopular(page: Int? = null): Flow<Movies>

    /*
        Gets the Top Rated Movies
     */
    suspend fun getTopRated(page: Int? = null): Flow<Movies>

    /*
        Gets the Upcoming Movies
     */
    suspend fun getUpcoming(page: Int? = null): Flow<Movies>

    /*
        Gets the Movie detail for a specific movie id
     */
    suspend fun getMovieDetail(id: Int? = null): Flow<MovieDetail>


}