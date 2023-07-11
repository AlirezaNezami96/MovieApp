package alireza.nezami.movieapp

import alireza.nezami.domain.usecase.GetGenreListUseCase
import alireza.nezami.model.genre.Genre
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getGenreListUseCase: GetGenreListUseCase,
) : ViewModel() {
    init {
        getGenreList()
    }

    val uiState: MutableStateFlow<MainActivityUiState> =
        MutableStateFlow(MainActivityUiState.Loading)

    private fun getGenreList() {
        viewModelScope.launch {
            getGenreListUseCase.invoke().map {
                uiState.value = MainActivityUiState.Success(it)
            }.stateIn(
                scope = viewModelScope,
                initialValue = MainActivityUiState.Loading,
                started = SharingStarted.WhileSubscribed(5_000),
            ).collect()
        }
    }
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val userData: List<Genre>) : MainActivityUiState
}
