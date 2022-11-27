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
                date = releaseDate.orEmpty(),
                overview = overview.orEmpty(),
                score = voteAverage ?: 0.0,
                thumbnail = backdropPath.orEmpty(),
                time = runtime ?: 0
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