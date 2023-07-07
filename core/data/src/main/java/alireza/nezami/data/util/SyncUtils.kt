package alireza.nezami.data.util

import alireza.nezami.database.dao.GenreDao
import alireza.nezami.database.entity.genre.GenreEntity
import alireza.nezami.model.genre.Genre
import alireza.nezami.network.dataSource.NetworkDataSource
import alireza.nezami.network.model.genre.GenreResponse
import alireza.nezami.network.model.genre.GetGenreListResponseDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GenreSynchronizer(
    private val localDataSource: GenreDao,
    private val remoteDataSource: NetworkDataSource,
    private val coroutineScope: CoroutineScope,
    private val mapper: GenreMapper,
) {
    private val synchronizationState: MutableStateFlow<SynchronizationState> =
        MutableStateFlow(SynchronizationState.Idle)

    val synchronizationStateFlow: StateFlow<SynchronizationState>
        get() = synchronizationState


    fun startSynchronization() {
        coroutineScope.launch {
            synchronize()
        }
    }

    fun stopSynchronization() {
        coroutineScope.cancel()
    }

    private suspend fun synchronize() {
        synchronizationState.value = SynchronizationState.InProgress

        try {
            localDataSource.getGenreList().map { localData ->
                if ( localData.isEmpty()) {
                    remoteDataSource.getGenreList().map { remoteData ->
                        if (remoteData.genres?.isNotEmpty() == true) {
                            val mappedData = mapper.mapToEntities(remoteData.genres.orEmpty())
                            localDataSource.insertOrReplaceGenreList(mappedData)
                            synchronizationState.value = SynchronizationState.Success(mappedData)
                        } else {
                            synchronizationState.value =
                                SynchronizationState.Error("Failed to fetch remote data")
                        }
                    }

                } else {
                    synchronizationState.value = SynchronizationState.Success(localData)
                }
            }

        } catch (e: Exception) {
            synchronizationState.value = SynchronizationState.Error(e.message ?: "Unknown error")
        }
    }
}

sealed class SynchronizationState {
    object Idle : SynchronizationState()
    object InProgress : SynchronizationState()
    data class Success(val data: List<GenreEntity>) : SynchronizationState()
    data class Error(val message: String) : SynchronizationState()
}


