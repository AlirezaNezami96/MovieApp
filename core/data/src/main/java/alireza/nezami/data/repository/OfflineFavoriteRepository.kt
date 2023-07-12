package alireza.nezami.data.repository

import alireza.nezami.database.dao.FavoriteDao
import alireza.nezami.database.entity.movie.MovieEntity
import alireza.nezami.database.entity.movie.asEntity
import alireza.nezami.database.entity.movie.asExternalModel
import alireza.nezami.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OfflineFavoriteRepository @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoritesRepository {
    override suspend fun getFavoriteMovies(): Flow<List<Movie>> =
        favoriteDao.getMovieDetail().map {
            it.map(MovieEntity::asExternalModel)
        }

    override suspend fun isMovieFavorite(id: Int): Flow<Boolean> =
        favoriteDao.getMovieDetail(id)
            .map { movieEntity -> movieEntity != null }

    override suspend fun deleteFavoriteMovie(id: Int) {
        favoriteDao.deleteFavorite(id)
    }

    override suspend fun addMovieToFavorite(movie: Movie) {
        favoriteDao.insertOrReplaceMovieToFavorite(movie.asEntity())
    }
}
