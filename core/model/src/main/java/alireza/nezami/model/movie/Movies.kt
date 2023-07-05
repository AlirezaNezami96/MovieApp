package alireza.nezami.model.movie

data class Movies(
    val dates: Dates,
    val page: Int,
    val results: List<MovieResult>,
    val totalPages: Int,
    val totalResults: Int
)