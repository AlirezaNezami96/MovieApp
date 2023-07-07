package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.FavoritesRepository
import javax.inject.Inject

class DeleteFavoriteMovieUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(id: Int) =
        favoritesRepository.deleteFavoriteMovie(id)
}
