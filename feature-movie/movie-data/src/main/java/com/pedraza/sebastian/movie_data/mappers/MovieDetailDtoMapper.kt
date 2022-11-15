package com.pedraza.sebastian.movie_data.mappers

import com.pedraza.sebastian.movie_data.entities.dto.MovieDetailDto
import com.pedraza.sebastian.movie_data.utils.EntityMapper
import com.pedraza.sebastian.movie_domain.models.MovieDetail

class MovieDetailDtoMapper : EntityMapper<MovieDetailDto, MovieDetail> {
    override fun mapFromEntity(entity: MovieDetailDto): MovieDetail {
        with(entity) {
            return MovieDetail(
                id = id,
                name = title,
                date = releaseDate,
                overview = overview,
                score = voteAverage,
                thumbnail = backdropPath,
                time = runtime
            )
        }
    }

    override fun mapToEntity(domainModel: MovieDetail): MovieDetailDto {
        with(domainModel) {
            return MovieDetailDto(
                id = id,
                title = name,
                releaseDate = date,
                overview = overview,
                voteAverage = score,
                backdropPath = thumbnail,
                runtime = time
            )
        }
    }
}