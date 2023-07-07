package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.model.movieDetial.MovieDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case for getting the movie detail.
 */
class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    /**
     * Retrieves the movie detail for the specified movie ID.
     *
     * @param id The ID of the movie.
     * @return Flow emitting the movie detail.
     */
    suspend operator fun invoke(id: Int): Flow<MovieDetail> =
        movieRepository.getMovieDetail(id)
}
