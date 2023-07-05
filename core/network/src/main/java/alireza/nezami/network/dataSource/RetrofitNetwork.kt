package alireza.nezami.network.dataSource

import alireza.nezami.network.api.NetworkApi
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetwork @Inject constructor(
    retrofit: Retrofit
) : NetworkDataSource {

    private val networkApi = retrofit
        .create(NetworkApi::class.java)

    override suspend fun getNowPlaying(page: Int?): List<Nothing> =
        networkApi.getNowPlaying(page = page).data


    override suspend fun getPopular(page: Int?): List<Nothing> =
        networkApi.getPopular(page = page).data

    override suspend fun getTopRated(page: Int?): List<Nothing> =
        networkApi.getTopRated(page = page).data

    override suspend fun getUpcoming(page: Int?): List<Nothing> =
        networkApi.getUpcoming(page = page).data

    override suspend fun getGenreList(): List<Nothing> =
        networkApi.getGenreList().data

    override suspend fun getMovieDetail(id: Int): List<Nothing> =
        networkApi.getMovieDetail(movieId = id).data


    override suspend fun searchMovies(query: String?, page: Int?): List<Nothing> =
        networkApi.searchMovies(query = query, page = page).data

}