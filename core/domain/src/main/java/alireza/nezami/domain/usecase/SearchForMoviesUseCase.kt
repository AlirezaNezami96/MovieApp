package alireza.nezami.domain.usecase

import alireza.nezami.data.repository.SearchRepository
import alireza.nezami.model.search.Search
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchForMoviesUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend operator fun invoke(query: String? = null, page: Int? = null): Flow<Search> =
        searchRepository.searchForMovies(query, page)
}
