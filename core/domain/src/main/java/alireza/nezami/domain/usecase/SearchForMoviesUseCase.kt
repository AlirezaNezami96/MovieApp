package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.SearchRepository
import alireza.nezami.model.search.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * A use case for searching movies.
 */
class SearchForMoviesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    /**
     * Searches movies based on the specified query and page.
     *
     * @param query The search query.
     * @param page The page number for paginated results.
     * @return Flow emitting the search results as a list of movies.
     */
    suspend operator fun invoke(query: String? = null, page: Int? = null): Flow<Search> =
        searchRepository.searchForMovies(query, page)
}
