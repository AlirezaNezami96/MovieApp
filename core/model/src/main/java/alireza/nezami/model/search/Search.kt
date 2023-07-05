package alireza.nezami.model.search

import alireza.nezami.model.movie.MovieResult

data class Search(
    val page: Int,
    val results: List<MovieResult>,
    val totalPages: Int,
    val totalResults: Int
)