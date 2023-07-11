package alireza.nezami.detail.presentation

import alireza.nezami.detail.presentation.contract.DetailIntent
import alireza.nezami.detail.presentation.contract.DetailUiState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()


    DetailContent(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
fun DetailContent(uiState: DetailUiState, onIntent: (DetailIntent) -> Unit) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp)
    ) {
        loading(uiState.isLoading)
    }

}

fun LazyListScope.loading(loading: Boolean) {
    item {
        if (loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(48.dp)
            )
        }
    }
}
