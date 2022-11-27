package com.pedraza.sebastian.movie_data.repositories

import com.google.common.truth.Truth.assertThat
import com.pedraza.sebastian.core.utils.Result
import com.pedraza.sebastian.core.utils.UiText
import com.pedraza.sebastian.movie_data.api.MoviesService
import com.pedraza.sebastian.movie_data.datasource.local.MoviesLocalDataSource
import com.pedraza.sebastian.movie_data.datasource.remote.MoviesRemoteDataSource
import com.pedraza.sebastian.movie_data.datasource.remote.MoviesRemoteDataSourceImpl
import com.pedraza.sebastian.movie_data.mappers.MovieDetailDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieListDtoMapper
import com.pedraza.sebastian.movie_data.mappers.MovieOrmMapper
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MoviesRepositoryImplTest {

    private lateinit var repository: MoviesRepositoryImpl
    private lateinit var remoteDataSource: MoviesRemoteDataSource
    private val mockLocalDataSource = mockk<MoviesLocalDataSource>()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var apiMock: MoviesService
    private lateinit var mockMovieListDtoMapper: MovieListDtoMapper
    private lateinit var mockMovieDetailDtoMapper: MovieDetailDtoMapper
    private val mockMovieOrmMapper: MovieOrmMapper = mockk(relaxed = true)


    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()

        apiMock = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(MoviesService::class.java)

        remoteDataSource = MoviesRemoteDataSourceImpl(apiMock, "api_mock")

        mockMovieListDtoMapper = MovieListDtoMapper(MovieDtoMapper())
        mockMovieDetailDtoMapper = MovieDetailDtoMapper()

        repository = MoviesRepositoryImpl(
            moviesRemoteDataSource = remoteDataSource,
            moviesLocalDataSource = mockLocalDataSource,
            movieListDtoMapper = mockMovieListDtoMapper,
            movieDetailDtoMapper = mockMovieDetailDtoMapper,
            movieOrmMapper = mockMovieOrmMapper
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        clearAllMocks()
    }

    private fun enqueueMockResponse(filename: String, code: Int = 200) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(filename)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockResponse.setResponseCode(code)
        mockWebServer.enqueue(mockResponse)
    }

    @Test
    fun `test getMovies with valid response returns Success result`() = runBlocking {
        //given
        enqueueMockResponse("get_movies_success.json")
        //when
        val result = repository.getMovieList(1)
        //then
        assertThat(result is Result.Success).isTrue()
        with(result.data!!.movies[0]) {
            assertThat(id).isEqualTo(436270)
            assertThat(name).isEqualTo("Black Adam")
        }
    }

    @Test
    fun `test getMovies with invalid response returns Error result`() = runBlocking {
        //given
        enqueueMockResponse("get_movies_success.json", 403)
        //when
        val result = repository.getMovieList(1)
        //then
        assertThat(result is Result.Error).isTrue()
        assertThat(result.data).isNull()
        assertThat(result.message is UiText.ResourcesString).isTrue()
    }


    @Test
    fun `test getMovies with malformed response returns Error result`() = runBlocking {
        //given
        enqueueMockResponse("get_movies_malformed.json")
        //when
        val result = repository.getMovieList(1)
        //then
        assertThat(result is Result.Error).isTrue()
        assertThat(result.data).isNull()
        assertThat(result.message is UiText.DynamicString).isTrue()
    }

    @Test
    fun `test getMovieDetail with valid response returns Success result`() = runBlocking {
        //given
        enqueueMockResponse("get_movie_detail_success.json")
        //when
        val result = repository.getMovieDetail(1)
        //then
        assertThat(result is Result.Success).isTrue()
        with(result.data!!) {
            assertThat(id).isEqualTo(436270)
            assertThat(name).isEqualTo("Black Adam")
            assertThat(overview).contains("Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian")
        }
    }

    @Test
    fun `test getMovieDetail with invalid response returns Error result`() = runBlocking {
        //given
        enqueueMockResponse("get_movie_detail_success.json", 403)
        //when
        val result = repository.getMovieDetail(1)
        //then
        assertThat(result is Result.Error).isTrue()
        assertThat(result.data).isNull()
        assertThat(result.message is UiText.ResourcesString).isTrue()
    }


    @Test
    fun `test getMovieDetail with malformed response returns Error result`() = runBlocking {
        //given
        enqueueMockResponse("get_movie_detail_malformed.json")
        //when
        val result = repository.getMovieDetail(1)
        //then
        assertThat(result is Result.Error).isTrue()
        assertThat(result.data).isNull()
        assertThat(result.message is UiText.DynamicString).isTrue()
    }

    @Test
    fun `test saveMovieToFavorites should call localDataSource`() = runBlocking {
        //given
        coEvery { mockLocalDataSource.insertMovie(any()) } just Runs
        //when
        repository.saveMovieToFavorites(mockk())
        //then
        coVerify(exactly = 1) { mockLocalDataSource.insertMovie(any()) }
    }

    @Test
    fun `test saveMovieToFavorites returns Success result`() = runBlocking {
        //given
        coEvery { mockLocalDataSource.insertMovie(any()) } just Runs
        //when
        val result = repository.saveMovieToFavorites(mockk())
        //then
        assertThat(result is Result.Success).isTrue()
    }

    @Test
    fun `test saveMovieToFavorites returns Error result`() = runBlocking {
        //given
        val messageException = "Invalid Test Exception"
        coEvery { mockLocalDataSource.insertMovie(any()) } throws Exception(messageException)
        //when
        val result = repository.saveMovieToFavorites(mockk())
        //then
        assertThat(result is Result.Error).isTrue()
        assertThat(result.message is UiText.DynamicString).isTrue()
    }
}