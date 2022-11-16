package com.pedraza.sebastian.movie_data.mappers

import com.pedraza.sebastian.movie_data.entities.dto.MovieListDto
import com.pedraza.sebastian.movie_data.utils.EntityMapper
import com.pedraza.sebastian.movie_domain.models.MovieList

class MovieListDtoMapper(
    private val movieDtoMapper: MovieDtoMapper
) : EntityMapper<MovieListDto, MovieList> {
    override fun mapFromEntity(entity: MovieListDto): MovieList {
        with(entity) {
            return MovieList(
                page = page,
                totalPages = totalPages,
                movies = movies.map { movieDtoMapper.mapFromEntity(it) },
                totalResults = totalResults
            )
        }
    }

    override fun mapToEntity(domainModel: MovieList): MovieListDto {
        with(domainModel) {
            return MovieListDto(
                page = page,
                totalPages = totalPages,
                movies = movies.map { movieDtoMapper.mapToEntity(it) },
                totalResults = totalResults
            )
        }
    }
}