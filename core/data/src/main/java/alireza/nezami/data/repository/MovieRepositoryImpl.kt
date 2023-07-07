package alireza.nezami.data.repository

import alireza.nezami.model.movie.Movies
import alireza.nezami.model.movieDetial.MovieDetail
import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movie.asExternalModel
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.movieDetial.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : MovieRepository {

    override suspend fun getNowPlaying(page: Int?): Flow<Movies> =
        networkDataSource.getNowPlaying(page = page)
            .map(GetMovieResponseDto::asExternalModel)

    override suspend fun getPopular(page: Int?): Flow<Movies> =
        networkDataSource.getPopular(page = page)
            .map(GetMovieResponseDto::asExternalModel)

    override suspend fun getTopRated(page: Int?): Flow<Movies> =
        networkDataSource.getTopRated(page = page)
            .map(GetMovieResponseDto::asExternalModel)

    override suspend fun getUpcoming(page: Int?): Flow<Movies> =
        networkDataSource.getUpcoming(page = page)
            .map(GetMovieResponseDto::asExternalModel)

    override suspend fun getMovieDetail(id: Int): Flow<MovieDetail> =
        networkDataSource.getMovieDetail(id = id)
            .map(GetMovieDetailResponseDto::asExternalModel)
}