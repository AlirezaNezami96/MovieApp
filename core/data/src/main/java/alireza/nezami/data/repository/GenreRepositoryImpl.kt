package alireza.nezami.data.repository

import alireza.nezami.model.genre.Genre
import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.model.genre.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GenreRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    //todo: add DAO for enabling offline first feature
) : GenreRepository {

    override suspend fun getGenreList(): Flow<List<Genre>> =
        networkDataSource.getGenreList()
            .map {
                it.genres?.map { genre ->
                    genre.asExternalModel()
                } ?: emptyList()
            }
}