package alireza.nezami.network.dataSource

import alireza.nezami.network.api.NetworkApi
import alireza.nezami.network.model.genre.GetGenreListResponseDto
import alireza.nezami.network.model.movie.GetMovieResponseDto
import alireza.nezami.network.model.movieDetial.GetMovieDetailResponseDto
import alireza.nezami.network.model.search.SearchMovieResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetwork @Inject constructor(
    retrofit: Retrofit
) : NetworkDataSource {

    private val networkApi = retrofit
        .create(NetworkApi::class.java)

    override suspend fun getNowPlaying(page: Int?): Flow<GetMovieResponseDto> = flow {
        emit(networkApi.getNowPlaying(page = page))
    }


    override suspend fun getPopular(page: Int?): Flow<GetMovieResponseDto> = flow {
        emit(networkApi.getPopular(page = page))
    }

    override suspend fun getTopRated(page: Int?): Flow<GetMovieResponseDto> = flow {
        emit(networkApi.getTopRated(page = page))
    }

    override suspend fun getUpcoming(page: Int?): Flow<GetMovieResponseDto> = flow {
        emit(networkApi.getUpcoming(page = page))
    }

    override suspend fun getGenreList(): Flow<GetGenreListResponseDto> = flow {
        emit(networkApi.getGenreList())
    }

    override suspend fun getMovieDetail(id: Int): Flow<GetMovieDetailResponseDto> = flow {
        emit(networkApi.getMovieDetail(movieId = id))
    }


    override suspend fun searchMovies(query: String?, page: Int?): Flow<SearchMovieResponseDto> =
        flow {
            emit(networkApi.searchMovies(query = query, page = page))
        }

}