package com.pedraza.sebastian.movie_data.datasource.remote

import com.google.common.truth.Truth.assertThat
import com.pedraza.sebastian.movie_data.api.MoviesService
import com.pedraza.sebastian.movie_data.entities.dto.MovieDetailDto
import com.pedraza.sebastian.movie_data.entities.dto.MovieListDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MoviesRemoteDataSourceImplTest {

    private lateinit var moviesRemoteDataSource: MoviesRemoteDataSource
    private val mockService = mockk<MoviesService>(relaxed = true)
    private val movieListDto = mockk<MovieListDto>(relaxed = true)
    private val movieDetailDto = mockk<MovieDetailDto>(relaxed = true)
    private val apiKey = "test_key"
    private val page = 1


    @Before
    fun setUp() {
        moviesRemoteDataSource = MoviesRemoteDataSourceImpl(mockService, apiKey)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test getMovieList should call movieService`() = runBlocking {
        //given
        val expectedResponse = Response.success(movieListDto)
        coEvery { mockService.getMovies(apiKey, page) } returns expectedResponse
        //when
        val response = moviesRemoteDataSource.getMovieList(1)
        //then
        coVerify(exactly = 1) { mockService.getMovies(apiKey, page) }
        assertThat(response).isEqualTo(expectedResponse)
    }

    @Test
    fun `test getMovieDetail should call movieService`() = runBlocking {
        //given
        val id = 1
        val expectedResponse = Response.success(movieDetailDto)
        coEvery { mockService.getMovieDetail(1, apiKey) } returns expectedResponse
        //when
        val response = moviesRemoteDataSource.getMovieDetail(1)
        //then
        coVerify(exactly = 1) { mockService.getMovieDetail(1, apiKey) }
        assertThat(response).isEqualTo(expectedResponse)
    }

    @Test
    fun `test getTopRatedMovies should call movieService`() = runBlocking {
        //given
        val expectedResponse = Response.success(movieListDto)
        coEvery { mockService.getTopRatedMovies(apiKey, page) } returns expectedResponse
        //when
        val response = moviesRemoteDataSource.getTopRatedMovies(1)
        //then
        coVerify(exactly = 1) { mockService.getTopRatedMovies(apiKey, page) }
        assertThat(response).isEqualTo(expectedResponse)
    }

    @Test
    fun `test getUpcomingMovies should call movieService`() = runBlocking {
        //given
        val expectedResponse = Response.success(movieListDto)
        coEvery { mockService.getUpcomingMovies(apiKey, page) } returns expectedResponse
        //when
        val response = moviesRemoteDataSource.getUpcomingMovies(1)
        //then
        coVerify(exactly = 1) { mockService.getUpcomingMovies(apiKey, page) }
        assertThat(response).isEqualTo(expectedResponse)
    }
}