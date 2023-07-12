package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.model.movie.Movies
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case for getting the list of upcoming movies.
 */
class GetUpcomingMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    /**
     * Retrieves the list of upcoming movies.
     *
     * @param page The page number of the movies to retrieve.
     * @return Flow emitting a list of upcoming movies.
     */
    suspend operator fun invoke(page: Int? = null): Flow<Movies> =
        movieRepository.getUpcoming(page)
}
