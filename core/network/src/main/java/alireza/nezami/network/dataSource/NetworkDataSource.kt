package alireza.nezami.network.dataSource

interface NetworkDataSource {
    suspend fun getNowPlaying(page: Int? = null): List<Nothing>

    suspend fun getPopular(page: Int? = null): List<Nothing>

    suspend fun getTopRated(page: Int? = null): List<Nothing>

    suspend fun getUpcoming(page: Int? = null): List<Nothing>

    suspend fun getGenreList(): List<Nothing>

    suspend fun getMovieDetail(id: Int): List<Nothing>

    suspend fun searchMovies(query: String? = null, page: Int? = null): List<Nothing>

}
