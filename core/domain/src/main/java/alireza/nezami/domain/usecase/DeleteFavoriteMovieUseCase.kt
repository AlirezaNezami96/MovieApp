package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.FavoritesRepository
import javax.inject.Inject

/**
 * A use case for deleting a movie from the favorite list.
 */
class DeleteFavoriteMovieUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    /**
     * Deletes a movie from the favorite list.
     *
     * @param id The ID of the movie to be deleted.
     */
    suspend operator fun invoke(id: Int) =
        favoritesRepository.deleteFavoriteMovie(id)
}
