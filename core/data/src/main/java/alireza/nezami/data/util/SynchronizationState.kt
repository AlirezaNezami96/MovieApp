package alireza.nezami.data.util

import alireza.nezami.database.entity.genre.GenreEntity

sealed class SynchronizationState {
    object Idle : SynchronizationState()
    object InProgress : SynchronizationState()
    data class Success(val data: List<GenreEntity>) : SynchronizationState()
    data class Error(val message: String) : SynchronizationState()
}
