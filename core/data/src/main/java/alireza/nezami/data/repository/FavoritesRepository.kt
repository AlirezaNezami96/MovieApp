package alireza.nezami.data.repository

import alireza.nezami.model.movie.Movie
import kotlinx.coroutines.flow.Flow


/**
 * Data layer interface for the movie feature.
 */
interface FavoritesRepository {

    /*
        Gets the Favorite Movies List
     */
    suspend fun getFavoriteMovies(): Flow<List<Movie>>

    /*
        Gets the Movie status if it's flagged as favorite
     */
    suspend fun isMovieFavorite(id: Int): Flow<Boolean>

    /*
        Deletes the selected movie from favorite list
     */
    suspend fun deleteFavoriteMovie(id: Int)

    /*
        Adds the movie to favorite list
     */
    suspend fun addMovieToFavorite(movie: Movie)
}