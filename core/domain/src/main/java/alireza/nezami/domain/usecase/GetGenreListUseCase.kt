package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.GenreRepository
import alireza.nezami.model.genre.Genre
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGenreListUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    suspend operator fun invoke(): Flow<List<Genre>> =
        genreRepository.getGenreList()
}
