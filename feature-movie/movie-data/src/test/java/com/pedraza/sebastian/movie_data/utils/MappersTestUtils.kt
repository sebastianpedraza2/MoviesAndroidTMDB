package com.pedraza.sebastian.movie_data.utils

import com.pedraza.sebastian.movie_data.entities.dto.MovieDetailDto
import com.pedraza.sebastian.movie_data.entities.dto.MovieDto
import com.pedraza.sebastian.movie_data.entities.dto.MovieListDto
import com.pedraza.sebastian.movie_domain.models.Movie
import com.pedraza.sebastian.movie_domain.models.MovieDetail
import com.pedraza.sebastian.movie_domain.models.MovieList


fun generateMockMovieDetailDto(
    releaseDate: String?,
    overview: String?,
    voteAverage: Double?,
    runeTime: Int?
) = MovieDetailDto(
    backdropPath = "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
    id = 436270,
    title = "Black Adam",
    releaseDate = releaseDate,
    overview = overview,
    voteAverage = voteAverage,
    runtime = runeTime
)

fun generateMockDomainMovieDetail(
) = MovieDetail(
    thumbnail = "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
    id = 436270,
    name = "Black Adam",
    date = "2022-10-19",
    overview = "description",
    score = 7.252,
    time = 125
)


fun generateMockMovieDto(
    releaseDate: String?,
    overview: String?,
    voteAverage: Double?,
    runeTime: Int?,
    thumbnail: String?
) = MovieDto(
    id = 505642,
    title = "Black Panther: Wakanda Forever",
    releaseDate = releaseDate,
    overview = overview,
    voteAverage = voteAverage,
    backdropPath = thumbnail
)

fun generateMockDomainMovie(
) = Movie(
    id = 505642,
    name = "Black Panther: Wakanda Forever",
    date = "2022-11-09",
    overview = "description",
    score = 7.5,
    thumbnail = "/xDMIl84Qo5Tsu62c9DGWhmPI67A.jpg"
)

fun generateMockMovieListDto(page: Int, totalPages: Int, totalResults: Int) = MovieListDto(
    page = page,
    totalPages = totalPages,
    movies = listOf(
        generateMockMovieDto(
            releaseDate = null,
            thumbnail = null,
            runeTime = null,
            voteAverage = null,
            overview = null
        )
    ),
    totalResults = totalResults
)

fun generateMockDomainMovieList(page: Int, totalPages: Int, totalResults: Int) = MovieList(
    page = page,
    totalPages = totalPages,
    movies = listOf(
        generateMockDomainMovie()
    ),
    totalResults = totalResults
)
