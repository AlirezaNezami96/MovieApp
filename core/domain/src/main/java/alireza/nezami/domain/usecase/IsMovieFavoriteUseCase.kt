package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.FavoritesRepository
import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.model.movieDetial.MovieDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case for getting the movie detail.
 */
class IsMovieFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    /**
     * Checks if the movie with the specified ID is a favorite.
     *
     * @param id The ID of the movie.
     * @return Flow emitting a Boolean indicating whether the movie is a favorite or not.
     */
    suspend operator fun invoke(id: Int): Flow<Boolean> =
        favoritesRepository.isMovieFavorite(id)
}
