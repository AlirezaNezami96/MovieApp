package alireza.nezami.network.api

import alireza.nezami.network.model.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int?,
    ): NetworkResponse<List<Nothing>>

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("page") page: Int?,
    ): NetworkResponse<List<Nothing>>

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int?,
    ): NetworkResponse<List<Nothing>>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int?,
    ): NetworkResponse<List<Nothing>>

    @GET("genre/movie/list")
    suspend fun getGenreList(): NetworkResponse<List<Nothing>>

    @GET("movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") movieId: Int,
    ): NetworkResponse<List<Nothing>>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("page") page: Int?,
        @Query("query") query: String?,
    ): NetworkResponse<List<Nothing>>

}