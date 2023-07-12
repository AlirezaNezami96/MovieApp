package alireza.nezami.data.util

import alireza.nezami.common.extensions.orZero
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.network.model.genre.GenreResponse
import javax.inject.Inject

/**
 * Mapper class that converts GenreResponse objects to GenreEntity objects.
 * Used for mapping data between network response and database entities.
 *
 */
class GenreMapper @Inject constructor() {

    /**
     * Maps a list of GenreResponse objects to a list of GenreEntity objects.
     *
     * @param genreResponses List of GenreResponse objects to be mapped.
     * @return List of GenreEntity objects.
     */
    fun mapToEntities(genreResponses: List<GenreResponse>): List<GenreEntity> {
        return genreResponses.map { response ->
            GenreEntity(
                name = response.name.orEmpty(),
                id = response.id.orZero()
            )
        }
    }
}
