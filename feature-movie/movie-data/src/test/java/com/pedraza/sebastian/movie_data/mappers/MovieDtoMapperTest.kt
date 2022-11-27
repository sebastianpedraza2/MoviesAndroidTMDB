package com.pedraza.sebastian.movie_data.mappers

import com.google.common.truth.Truth
import com.pedraza.sebastian.movie_data.utils.generateMockDomainMovie
import com.pedraza.sebastian.movie_data.utils.generateMockMovieDto
import org.junit.Before
import org.junit.Test

class MovieDtoMapperTest {
    private lateinit var movieDtoMapper: MovieDtoMapper

    @Before
    fun setUp() {
        movieDtoMapper = MovieDtoMapper()
    }

    @Test
    fun `test MovieMapper's mapFromEntity works correctly`() {
        //given
        val movieDto = generateMockMovieDto(
            thumbnail = "/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg",
            releaseDate = "2022-10-19",
            overview = "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            voteAverage = 7.252,
            runeTime = 125
        )
        //when
        val domainMovie = movieDtoMapper.mapFromEntity(movieDto)
        //then
        with(domainMovie) {
            Truth.assertThat(id).isEqualTo(505642)
            Truth.assertThat(name).isEqualTo("Black Panther: Wakanda Forever")
            Truth.assertThat(date).isEqualTo("2022-10-19")
            Truth.assertThat(score).isEqualTo(7.252)
            Truth.assertThat(overview)
                .contains("Nearly 5,000 years after he was bestowed with the almighty powers of the")
        }
    }

    @Test
    fun `test MovieMapper's mapFromEntity with null values`() {
        //given
        val movieDto = generateMockMovieDto(
            thumbnail = null,
            releaseDate = null,
            overview = null,
            voteAverage = null,
            runeTime = null
        )
        //when
        val domainMovie = movieDtoMapper.mapFromEntity(movieDto)
        //then
        with(domainMovie) {
            Truth.assertThat(id).isEqualTo(505642)
            Truth.assertThat(name).isEqualTo("Black Panther: Wakanda Forever")
            Truth.assertThat(date).isNotNull()
            Truth.assertThat(score).isNotNull()
            Truth.assertThat(overview).isNotNull()
            Truth.assertThat(thumbnail).isNotNull()
        }
    }


    @Test
    fun `test MovieMapper's mapToEntity works correctly`() {
        //given
        val domainMovie = generateMockDomainMovie()
        //when
        val movieDto = movieDtoMapper.mapToEntity(domainMovie)
        //then
        with(movieDto) {
            Truth.assertThat(id).isEqualTo(505642)
            Truth.assertThat(title).isEqualTo("Black Panther: Wakanda Forever")
            Truth.assertThat(releaseDate).isEqualTo("2022-11-09")
            Truth.assertThat(voteAverage).isEqualTo(7.5)
            Truth.assertThat(overview).isEqualTo("description")
        }
    }
}