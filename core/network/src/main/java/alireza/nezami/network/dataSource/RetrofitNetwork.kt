package alireza.nezami.network.dataSource

import alireza.nezami.network.api.NetworkApi
import alireza.nezami.network.model.genre.GetGenreListResponseDto
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.search.SearchMovieResponseDto
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetwork @Inject constructor(
    retrofit: Retrofit
) : NetworkDataSource {

    private val networkApi = retrofit
        .create(NetworkApi::class.java)

    override suspend fun getNowPlaying(page: Int?): GetMovieResponseDto =
        networkApi.getNowPlaying(page = page).data


    override suspend fun getPopular(page: Int?): GetMovieResponseDto =
        networkApi.getPopular(page = page).data

    override suspend fun getTopRated(page: Int?): GetMovieResponseDto =
        networkApi.getTopRated(page = page).data

    override suspend fun getUpcoming(page: Int?): GetMovieResponseDto =
        networkApi.getUpcoming(page = page).data

    override suspend fun getGenreList(): GetGenreListResponseDto =
        networkApi.getGenreList().data

    override suspend fun getMovieDetail(id: Int): GetMovieDetailResponseDto =
        networkApi.getMovieDetail(movieId = id).data


    override suspend fun searchMovies(query: String?, page: Int?): SearchMovieResponseDto =
        networkApi.searchMovies(query = query, page = page).data

}