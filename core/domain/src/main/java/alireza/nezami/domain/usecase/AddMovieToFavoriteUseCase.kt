package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.FavoritesRepository
import alireza.nezami.model.movie.Movie
import javax.inject.Inject

/**
 * A use case for adding a movie to the favorite list.
 */
class AddMovieToFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    /**
     * Adds a movie to the favorite list.
     *
     * @param movie The movie to be added.
     */
    suspend operator fun invoke(movie: Movie) =
        favoritesRepository.addMovieToFavorite(movie)
}
