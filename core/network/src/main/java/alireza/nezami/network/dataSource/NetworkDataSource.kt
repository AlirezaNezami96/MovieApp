package alireza.nezami.network.dataSource

import alireza.nezami.network.model.genre.GetGenreListResponseDto
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.search.SearchMovieResponseDto

interface NetworkDataSource {
    suspend fun getNowPlaying(page: Int? = null): GetMovieResponseDto

    suspend fun getPopular(page: Int? = null): GetMovieResponseDto

    suspend fun getTopRated(page: Int? = null): GetMovieResponseDto

    suspend fun getUpcoming(page: Int? = null): GetMovieResponseDto

    suspend fun getGenreList(): GetGenreListResponseDto

    suspend fun getMovieDetail(id: Int): GetMovieDetailResponseDto

    suspend fun searchMovies(query: String? = null, page: Int? = null): SearchMovieResponseDto

}
