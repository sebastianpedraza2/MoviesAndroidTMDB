package com.pedraza.sebastian.movie_data.mappers

import com.pedraza.sebastian.movie_data.entities.orm.MovieOrm
import com.pedraza.sebastian.movie_data.utils.EntityMapper
import com.pedraza.sebastian.movie_domain.models.Movie

class MovieOrmMapper : EntityMapper<MovieOrm, Movie> {
    override fun mapFromEntity(entity: MovieOrm): Movie {
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

    override fun mapToEntity(domainModel: Movie): MovieOrm {
        with(domainModel) {
            return MovieOrm(
                id = id,
                title = name,
                releaseDate = date,
                overview = overview,
                voteAverage = score,
                backdropPath = thumbnail
            )
        }
    }

    fun mapFromEntityList(initial: List<MovieOrm>): List<Movie> =
        initial.map { mapFromEntity(it) }
}