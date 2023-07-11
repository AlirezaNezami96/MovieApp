package alireza.nezami.data.util

import alireza.nezami.database.dao.GenreDao
import alireza.nezami.network.dataSource.NetworkDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class GenreSynchronizer @Inject constructor(
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
                if (localData.isEmpty()) {
                    remoteDataSource.getGenreList().map { remoteData ->
                        if (remoteData.genres?.isNotEmpty() == true) {
                            val mappedData = mapper.mapToEntities(remoteData.genres.orEmpty())
                            localDataSource.insertOrReplaceGenreList(mappedData)
                            synchronizationState.value = SynchronizationState.Success(mappedData)
                        } else {
                            synchronizationState.value =
                                SynchronizationState.Error("Failed to fetch remote data")
                        }
                    }.catch {
                        synchronizationState.value =
                            SynchronizationState.Error("Failed to fetch remote data")
                    }.collect()

                } else {
                    synchronizationState.value = SynchronizationState.Success(localData)
                }
            }.catch {
                synchronizationState.value =
                    SynchronizationState.Error("Failed to fetch local data")
                Timber.i("syncing genre list: ${it.message}")
            }.collect()

        } catch (e: Exception) {
            synchronizationState.value = SynchronizationState.Error(e.message ?: "Unknown error")
        }
    }
}