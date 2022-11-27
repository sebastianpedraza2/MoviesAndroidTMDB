package com.pedraza.sebastian.movie_data.mappers

import com.google.common.truth.Truth.assertThat
import com.pedraza.sebastian.movie_data.utils.generateMockDomainMovieDetail
import com.pedraza.sebastian.movie_data.utils.generateMockMovieDetailDto
import org.junit.Before
import org.junit.Test

class MovieDetailDtoMapperTest {

    private lateinit var movieDetailDtoMapper: MovieDetailDtoMapper

    @Before
    fun setUp() {
        movieDetailDtoMapper = MovieDetailDtoMapper()
    }

    @Test
    fun `test MovieDetailMapper's mapFromEntity works correctly`() {
        //given
        val movieDetailDto = generateMockMovieDetailDto(
            releaseDate = "2022-10-19",
            overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            voteAverage = 7.252,
            runeTime = 125
        )
        //when
        val domainMovieDetail = movieDetailDtoMapper.mapFromEntity(movieDetailDto)
        //then
        with(domainMovieDetail) {
            assertThat(id).isEqualTo(436270)
            assertThat(name).isEqualTo("Black Adam")
            assertThat(date).isEqualTo("2022-10-19")
            assertThat(score).isEqualTo(7.252)
        }
    }

    @Test
    fun `test MovieDetailMapper's mapFromEntity with null values`() {
        //given
        val movieDetailDto = generateMockMovieDetailDto(
            releaseDate = null,
            overview = null,
            voteAverage = null,
            runeTime = null
        )
        //when
        val domainMovieDetail = movieDetailDtoMapper.mapFromEntity(movieDetailDto)
        //then
        with(domainMovieDetail) {
            assertThat(id).isEqualTo(436270)
            assertThat(name).isEqualTo("Black Adam")
            assertThat(date).isNotNull()
            assertThat(overview).isNotNull()
            assertThat(score).isNotNull()
            assertThat(time).isNotNull()
        }
    }


    @Test
    fun `test MovieDetailMapper's mapToEntity works correctly`() {
        //given
        val movieDetail = generateMockDomainMovieDetail()
        //when
        val movieDetailDto = movieDetailDtoMapper.mapToEntity(movieDetail)
        //then
        with(movieDetailDto) {
            assertThat(id).isEqualTo(436270)
            assertThat(title).isEqualTo("Black Adam")
            assertThat(releaseDate).isEqualTo("2022-10-19")
            assertThat(voteAverage).isEqualTo(7.252)
        }
    }
}