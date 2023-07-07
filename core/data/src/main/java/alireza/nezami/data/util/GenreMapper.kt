package alireza.nezami.data.util

import alireza.nezami.common.extensions.orZero
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.network.model.genre.GenreResponse
import javax.inject.Inject

class GenreMapper @Inject constructor() {
    fun mapToEntities(genreResponses: List<GenreResponse>): List<GenreEntity> {
        return genreResponses.map { response ->
            GenreEntity(
                name = response.name.orEmpty(),
                id = response.id.orZero()
            )
        }
    }
}
