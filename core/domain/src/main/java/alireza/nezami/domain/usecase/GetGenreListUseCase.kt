package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.GenreRepository
import alireza.nezami.model.genre.Genre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case for getting the genre list.
 */
class GetGenreListUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    /**
     * Retrieves the list of genres.
     *
     * @return Flow emitting a list of genres.
     */
    suspend operator fun invoke(): Flow<List<Genre>> =
        genreRepository.getGenreList()
}
