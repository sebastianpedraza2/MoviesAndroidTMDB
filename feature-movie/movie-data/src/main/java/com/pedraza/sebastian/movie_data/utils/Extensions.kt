package com.pedraza.sebastian.movie_data.utils

import com.pedraza.sebastian.core.R
import com.pedraza.sebastian.core.utils.UiText
import retrofit2.Response
import com.pedraza.sebastian.core.utils.Result

/**
 * Parses a retrofit response object into an entity using the corresponding [entityMapper]
 * and returns the result if network request was successful
 */
fun <T, E> Response<T>.toResult(
    entityMapper: EntityMapper<T, E>
): Result<E> {
    val body = this.body()
    return if (body != null && this.isSuccessful) {
        Result.Success(entityMapper.mapFromEntity(body))
    } else {
        Result.Error(UiText.ResourcesString(R.string.something_went_wrong))
    }
}