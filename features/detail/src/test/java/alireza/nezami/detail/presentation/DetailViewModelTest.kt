package alireza.nezami.detail.presentation

import alireza.nezami.common.result.Result
import alireza.nezami.common.result.asResult
import alireza.nezami.detail.presentation.contract.DetailUiState
import alireza.nezami.domain.usecase.AddMovieToFavoriteUseCase
import alireza.nezami.domain.usecase.DeleteFavoriteMovieUseCase
import alireza.nezami.domain.usecase.GetMovieDetailUseCase
import alireza.nezami.domain.usecase.IsMovieFavoriteUseCase
import alireza.nezami.model.movieDetial.MovieDetail
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.RegisterExtension

@ExtendWith(MockKExtension::class, MainDispatcherExtension::class)
class DetailViewModelTest {


    lateinit var sut: DetailViewModel

    @RelaxedMockK
    lateinit var movieDetailUseCase: GetMovieDetailUseCase
    @RelaxedMockK
    lateinit var isMovieFavoriteUseCase: IsMovieFavoriteUseCase
    @RelaxedMockK
    lateinit var addMovieToFavoriteUseCase: AddMovieToFavoriteUseCase
    @RelaxedMockK
    lateinit var deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase

    @SpyK
    var savedStateHandle: SavedStateHandle = SavedStateHandle()
    @RelaxedMockK
    lateinit var detailUiState: DetailUiState



    @BeforeEach
    fun init() {
        sut = DetailViewModel(
            savedStateHandle,
            detailUiState,
            movieDetailUseCase,
            isMovieFavoriteUseCase,
            addMovieToFavoriteUseCase,
            deleteFavoriteMovieUseCase
        )
    }

    @Test
    fun `when init sut should show loading and hide error`()= runTest{
        val movieDetail = MovieDetail()
        coEvery { movieDetailUseCase(any()).asResult() } returns flowOf(Result.Success(movieDetail))

        val result = sut.uiState.first()
        println(result.movieDetail == null)
        assert(result.movieDetail == null)
    }

}