package com.pedraza.sebastian.movie_presentation.movie_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.core.utils.UiEvent
import com.pedraza.sebastian.movie_domain.usecases.movie_detail.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun getMovieDetail(id: Int) {
        viewModelScope.launch(dispatcher) {
            _uiState.update { currentState ->
                currentState.copy(movieDetailUiState = MovieDetailUiState.Loading)
            }
            when (val response = getMovieDetailUseCase.invoke(id)) {
                is Result.Success -> {
                    _uiState.update { currentState ->
                        currentState.copy(
                            movieDetail = response.value,
                            movieDetailUiState = MovieDetailUiState.MovieDetailFetched
                        )
                    }
                }
                is Result.Error -> {
                    _uiEvent.send(UiEvent.ShowSnackBar(response.message))
                    _uiState.update { currentState ->
                        currentState.copy(
                            movieDetailUiState = MovieDetailUiState.MovieDetailFetched
                        )
                    }
                }
            }
        }
    }

    fun onNavigateBack() {
        viewModelScope.launch(dispatcher) {
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }

}