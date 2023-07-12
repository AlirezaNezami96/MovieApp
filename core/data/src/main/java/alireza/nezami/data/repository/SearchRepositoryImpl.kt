package alireza.nezami.data.repository

import alireza.nezami.database.dao.GenreDao
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.model.search.Search
import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movie.asExternalModel
import alireza.nezami.network.model.search.SearchMovieResponseDto
import alireza.nezami.network.model.search.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val genreDao: GenreDao
) : SearchRepository {

    override suspend fun searchForMovies(query: String?, page: Int?): Flow<Search> =flow{
        val searchFlow = networkDataSource.searchMovies(page = page, query = query)
        val genreListFlow = genreDao.getGenreList()

        combine(searchFlow, genreListFlow) { movies, genres ->
            mapGenreNames(movies, genres)
        }.collect { emit(it.asExternalModel()) }
    }

    private fun mapGenreNames(
        movies: SearchMovieResponseDto,
        genres: List<GenreEntity>
    ): SearchMovieResponseDto {
        val updatedResults = movies.results?.map { movieResult ->
            val genreNames = movieResult.genreIds?.map { genreId ->
                genres.find { it.id == genreId }?.name.orEmpty()
            } ?: emptyList()
            movieResult.genreNames = genreNames
            movieResult
        }
        return movies.copy(results = updatedResults)
    }
}