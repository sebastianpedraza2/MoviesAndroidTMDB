package com.pedraza.sebastian.movie_data.mappers

import com.pedraza.sebastian.movie_data.entities.dto.MovieDto
import com.pedraza.sebastian.movie_data.utils.EntityMapper
import com.pedraza.sebastian.movie_domain.models.Movie

class MovieDtoMapper : EntityMapper<MovieDto, Movie> {
    override fun mapFromEntity(entity: MovieDto): Movie {
        with(entity) {
            return Movie(
                id = id,
                name = title,
                date = releaseDate,
                overview = overview,
                score = voteAverage,
                thumbnail = backdropPath
            )
        }
    }

    override fun mapToEntity(domainModel: Movie): MovieDto {
        with(domainModel) {
            return MovieDto(
                id = id,
                title = name,
                releaseDate = date,
                overview = overview,
                voteAverage = score,
                backdropPath = thumbnail ?: ""
            )
        }
    }
}