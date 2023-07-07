package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.FavoritesRepository
import alireza.nezami.model.movie.Movie
import javax.inject.Inject

class AddMovieToFavoriteUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(movie: Movie) =
        favoritesRepository.addMovieToFavorite(movie)
}
