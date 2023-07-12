package alireza.nezami.network.dataSource

import alireza.nezami.network.model.genre.GetGenreListResponseDto
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.search.SearchMovieResponseDto
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {
    suspend fun getNowPlaying(page: Int? = null): Flow<GetMovieResponseDto>

    suspend fun getPopular(page: Int? = null): Flow<GetMovieResponseDto>

    suspend fun getTopRated(page: Int? = null): Flow<GetMovieResponseDto>

    suspend fun getUpcoming(page: Int? = null): Flow<GetMovieResponseDto>

    suspend fun getGenreList(): Flow<GetGenreListResponseDto>

    suspend fun getMovieDetail(id: Int): Flow<GetMovieDetailResponseDto>

    suspend fun searchMovies(query: String? = null, page: Int? = null): Flow<SearchMovieResponseDto>

}
