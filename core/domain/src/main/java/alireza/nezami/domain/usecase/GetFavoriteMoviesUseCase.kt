package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.FavoritesRepository
import alireza.nezami.domain.usecase.FavoriteMoviesSortType.NONE
import alireza.nezami.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * A use case for getting the favorite movies list.
 */
class GetFavoriteMoviesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    /**
     * Retrieves the list of favorite movies.
     *
     * @param sortBy The sorting type for the favorite movies.
     * @return Flow emitting a list of favorite movies sorted according to the specified sorting type.
     */
    suspend operator fun invoke(sortBy: FavoriteMoviesSortType = NONE): Flow<List<Movie>> =
        favoritesRepository.getFavoriteMovies().map { movies ->
            when (sortBy) {
                FavoriteMoviesSortType.POPULARITY -> movies.sortedByDescending { it.popularity }
                FavoriteMoviesSortType.VOTE_AVERAGE -> movies.sortedByDescending { it.voteAverage }
                FavoriteMoviesSortType.RELEASE_DATE -> movies.sortedByDescending { it.releaseDate }
                FavoriteMoviesSortType.GENRE -> movies.sortedBy { it.genreIds.joinToString() }
                else -> movies
            }
        }
}


enum class FavoriteMoviesSortType {
    NONE,
    POPULARITY,
    VOTE_AVERAGE,
    RELEASE_DATE,
    GENRE
}

