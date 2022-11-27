package com.pedraza.sebastian.movie_data.mappers

import com.google.common.truth.Truth.assertThat
import com.pedraza.sebastian.movie_data.utils.*
import org.junit.Before
import org.junit.Test

class MovieListDtoMapperTest {
    private lateinit var movieListDtoMapper: MovieListDtoMapper

    @Before
    fun setUp() {
        movieListDtoMapper = MovieListDtoMapper(MovieDtoMapper())
    }

    @Test
    fun `test MovieListMapper's mapFromEntity works correctly`() {
        //given
        val movieListDto = generateMockMovieListDto(
            totalPages = 36068,
            totalResults = 721357,
            page = 1
        )
        //when
        val domainMovieList = movieListDtoMapper.mapFromEntity(movieListDto)
        //then
        with(domainMovieList) {
            assertThat(page).isEqualTo(1)
            assertThat(totalPages).isEqualTo(36068)
            assertThat(totalResults).isEqualTo(721357)
        }
    }

    @Test
    fun `test MovieListMapper's mapToEntity works correctly`() {
        //given
        val movieDomainList = generateMockDomainMovieList(
            totalPages = 36068,
            totalResults = 721357,
            page = 1
        )
        //when
        val movieListDto = movieListDtoMapper.mapToEntity(movieDomainList)
        //then
        with(movieListDto) {
            assertThat(page).isEqualTo(1)
            assertThat(totalPages).isEqualTo(36068)
            assertThat(totalResults).isEqualTo(721357)
        }
    }
}