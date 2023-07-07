package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.MovieRepository
import alireza.nezami.model.movieDetial.MovieDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(id: Int): Flow<MovieDetail> =
        movieRepository.getMovieDetail(id)
}
