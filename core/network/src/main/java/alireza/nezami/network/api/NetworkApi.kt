package alireza.nezami.network.api

import alireza.nezami.network.model.NetworkResponse
import alireza.nezami.network.model.genre.GetGenreListResponseDto
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.search.SearchMovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int?,
    ): NetworkResponse<GetMovieResponseDto>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int?,
    ): NetworkResponse<GetMovieResponseDto>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int?,
    ): NetworkResponse<GetMovieResponseDto>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int?,
    ): NetworkResponse<GetMovieResponseDto>

    @GET("genre/movie/list")
    suspend fun getGenreList(): NetworkResponse<GetGenreListResponseDto>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
    ): NetworkResponse<GetMovieDetailResponseDto>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("page") page: Int?,
        @Query("query") query: String?,
    ): NetworkResponse<SearchMovieResponseDto>

}