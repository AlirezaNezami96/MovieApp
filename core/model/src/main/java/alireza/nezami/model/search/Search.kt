package alireza.nezami.model.search

import alireza.nezami.model.movie.Movie

data class Search(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)