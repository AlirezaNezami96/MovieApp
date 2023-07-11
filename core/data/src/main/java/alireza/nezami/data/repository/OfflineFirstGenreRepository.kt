package alireza.nezami.data.repository

import alireza.nezami.data.util.GenreMapper
import alireza.nezami.data.util.GenreSynchronizer
import alireza.nezami.data.util.SynchronizationState
import alireza.nezami.database.dao.GenreDao
import alireza.nezami.database.entity.genre.asExternalModel
import alireza.nezami.model.genre.Genre
import alireza.nezami.network.dataSource.NetworkDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class OfflineFirstGenreRepository @Inject constructor(
    private val genreSynchronizer: GenreSynchronizer
) : GenreRepository {

    override suspend fun getGenreList(): Flow<List<Genre>> = flow {

        val stateFlow = genreSynchronizer.synchronizationStateFlow

        stateFlow.collect { state ->
            when (state) {
                is SynchronizationState.Success -> {
                    emit(state.data.map { it.asExternalModel() })
                }

                is SynchronizationState.Error -> {
                    val errorMessage = state.message
                    // Handle synchronization error state
                }

                else -> Unit
            }
        }
    }.onStart {
        genreSynchronizer.startSynchronization()
    }.onCompletion {
        genreSynchronizer.stopSynchronization()
    }
}