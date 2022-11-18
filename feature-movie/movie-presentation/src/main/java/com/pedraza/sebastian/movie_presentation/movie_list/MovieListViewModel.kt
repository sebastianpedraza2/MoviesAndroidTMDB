package com.pedraza.sebastian.movie_presentation.movie_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedraza.sebastian.core.navigation.Routes
import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.core.utils.UiEvent
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.models.MovieList
import com.pedraza.sebastian.movie_domain.usecases.favorites.DeleteMovieFromFavoritesUseCase
import com.pedraza.sebastian.movie_domain.usecases.favorites.GetAllMoviesFromFavoritesUseCase
import com.pedraza.sebastian.movie_domain.usecases.favorites.SaveMovieToFavoritesUseCase
import com.pedraza.sebastian.movie_domain.usecases.movie_list.GetMovieListUseCase
import com.pedraza.sebastian.movie_domain.usecases.top_rated_movies.GetTopRatedMovieListUseCase
import com.pedraza.sebastian.movie_domain.usecases.upcoming_movies.GetUpcomingMovieListUseCase
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
class MovieListViewModel @Inject constructor(
    private val getMovieListUseCase: GetMovieListUseCase,
    private val getAllMoviesFromFavoritesUseCase: GetAllMoviesFromFavoritesUseCase,
    private val getTopRatedMovieListUseCase: GetTopRatedMovieListUseCase,
    private val getUpcomingMovieListUseCase: GetUpcomingMovieListUseCase,
    private val saveMovieToFavoritesUseCase: SaveMovieToFavoritesUseCase,
    private val deleteMovieFromFavoritesUseCase: DeleteMovieFromFavoritesUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieListState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onMovieListEvent(event: MovieListEvent) {
        when (event) {
            is MovieListEvent.GetMovieList -> {
                changeStateOnEvent(event = event, filterMoviesBy = FilterMoviesBy.NoFilter)
            }
            is MovieListEvent.FilterByUpcomingPressed -> {
                changeStateOnEvent(event = event, filterMoviesBy = FilterMoviesBy.Upcoming)
            }
            is MovieListEvent.FilterByTopRatedPressed -> {
                changeStateOnEvent(event = event, filterMoviesBy = FilterMoviesBy.TopRated)
            }
            is MovieListEvent.OnFavoritePressed -> {
                addMovieToFavorites(event.movie)
            }
        }
    }

    fun getMoviesList() {
        viewModelScope.launch(dispatcher) {
            if (_uiState.value.nextPage == 1)
                _uiState.update { currentState ->
                    currentState.copy(
                        movieListUiState = MovieListUiState.Loading,
                        movieList = emptyList()
                    )
                }
            when (val response = getMovieListByFilter()) {
                is Result.Success -> {
                    val movies = mapFavoriteMovies(response.value.movies)

                    _uiState.update { currentState ->
                        val currentMovieList = currentState.movieList.toMutableList().apply {
                            if (this.isNotEmpty()) removeLast()
                            addAll(movies)
                            add(Movie())
                        }
                        currentState.copy(
                            movieList = currentMovieList,
                            movieListUiState = MovieListUiState.MovieListFetched
                        )
                    }
                }
                is Result.Error -> {
                    _uiEvent.send(UiEvent.ShowSnackBar(response.message))
                    _uiState.update { currentState -> currentState.copy(movieListUiState = MovieListUiState.MovieListFetched) }
                }
            }
            _uiState.update { currentState -> currentState.copy(nextPage = currentState.nextPage.inc()) }
        }
    }

    fun onNavigateToMovieDetail(id: Int) {
        viewModelScope.launch(dispatcher) {
            _uiEvent.send(UiEvent.Navigate("${Routes.MOVIE_DETAIL}/${id}"))
        }
    }

    private fun changeStateOnEvent(event: MovieListEvent, filterMoviesBy: FilterMoviesBy) {
        if (_uiState.value.currentEvent != event) {
            _uiState.update { currentState ->
                currentState.copy(
                    filterBy = filterMoviesBy,
                    nextPage = 1,
                    currentEvent = event
                )
            }
            getMoviesList()
        }
    }

    private suspend fun getMovieListByFilter(): Result<MovieList> {
        return when (_uiState.value.filterBy) {
            FilterMoviesBy.NoFilter -> getMovieListUseCase.invoke(_uiState.value.nextPage)
            FilterMoviesBy.TopRated -> getTopRatedMovieListUseCase.invoke(_uiState.value.nextPage)
            FilterMoviesBy.Upcoming -> getUpcomingMovieListUseCase.invoke(_uiState.value.nextPage)
        }
    }

    fun getFavoriteMovies() {
        viewModelScope.launch(dispatcher) {
            when (val response = getAllMoviesFromFavoritesUseCase.invoke()) {
                is Result.Success -> {
                    response.value.collect { movieListFromFavorites ->
                        _uiState.update { currentState -> currentState.copy(favoritesMovieList = movieListFromFavorites) }
                    }
                }
                is Result.Error -> {
                    _uiEvent.send(UiEvent.ShowSnackBar(response.message))
                }
            }
        }
    }

    private fun addMovieToFavorites(movie: Movie) {
        val currentIsFavoriteState = movie.isFavorite
        _uiState.value.movieList.first { it.id == movie.id }.isFavorite = !currentIsFavoriteState

        viewModelScope.launch(dispatcher) {
            val isFavorite = _uiState.value.movieList.first { movie.id == it.id }.isFavorite
            if (isFavorite) saveMovieToFavoritesUseCase.invoke(movie)
            else deleteMovieFromFavoritesUseCase.invoke(movie)
        }
    }

    private fun mapFavoriteMovies(movieList: List<Movie>): List<Movie> {
        return movieList.map { movie ->
            movie.isFavorite =
                _uiState.value.favoritesMovieList.firstOrNull { it.id == movie.id } != null
            return@map movie
        }
    }
}