package alireza.nezami.data.repository

import alireza.nezami.database.dao.GenreDao
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.model.movie.Movies
import alireza.nezami.model.movieDetial.MovieDetail
import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movie.asExternalModel
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.movieDetial.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val genreDao: GenreDao
) : MovieRepository {
    override suspend fun getUpcoming(page: Int?): Flow<Movies> = flow {
        val upcomingFlow = networkDataSource.getUpcoming(page = page)
        val genreListFlow = genreDao.getGenreList()

        combine(upcomingFlow, genreListFlow) { movies, genres ->
            mapGenreNames(movies, genres)
        }.collect { emit(it.asExternalModel()) }
    }

    override suspend fun getNowPlaying(page: Int?): Flow<Movies> = flow {
        val nowPlayingFlow = networkDataSource.getNowPlaying(page = page)
        val genreListFlow = genreDao.getGenreList()

        combine(nowPlayingFlow, genreListFlow) { movies, genres ->
            mapGenreNames(movies, genres)
        }.collect { emit(it.asExternalModel()) }
    }

    override suspend fun getPopular(page: Int?): Flow<Movies> = flow {
        val popularFlow = networkDataSource.getPopular(page = page)
        val genreListFlow = genreDao.getGenreList()

        combine(popularFlow, genreListFlow) { movies, genres ->
            mapGenreNames(movies, genres)
        }.collect { emit(it.asExternalModel()) }
    }

    override suspend fun getTopRated(page: Int?): Flow<Movies> = flow {
        val topRatedFlow = networkDataSource.getTopRated(page = page)
        val genreListFlow = genreDao.getGenreList()

        combine(topRatedFlow, genreListFlow) { movies, genres ->
            mapGenreNames(movies, genres)
        }.collect { emit(it.asExternalModel()) }
    }

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetail> =
        networkDataSource.getMovieDetail(id = id)
            .map(GetMovieDetailResponseDto::asExternalModel)
}

private fun mapGenreNames(
    movies: GetMovieResponseDto,
    genres: List<GenreEntity>
): GetMovieResponseDto {
    val updatedResults = movies.results?.map { movieResult ->
        val genreNames = movieResult.genreIds?.map { genreId ->
            genres.find { it.id == genreId }?.name.orEmpty()
        } ?: emptyList()
        movieResult.genreNames = genreNames
        movieResult
    }
    return movies.copy(results = updatedResults)
}