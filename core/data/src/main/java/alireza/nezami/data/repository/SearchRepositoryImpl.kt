package alireza.nezami.data.repository

import alireza.nezami.model.search.Search
import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.model.search.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : SearchRepository {

    override suspend fun searchForMovies(query: String?, page: Int?): Flow<Search> =
        networkDataSource.searchMovies(page = page, query = query)
            .map {
                it.asExternalModel()
            }

}