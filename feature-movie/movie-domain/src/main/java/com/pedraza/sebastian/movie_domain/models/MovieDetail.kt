package com.pedraza.sebastian.movie_domain.models

import com.pedraza.sebastian.kotlin_helpers.Constants

data class MovieDetail(
    val id: Int = 0,
    val name: String = "",
    val date: String = "",
    val overview: String = "",
    val time: Int = 0,
    val score: Double = 0.0,
    val thumbnail: String = ""
){
    fun getCompleteThumbnailUrl() = "${Constants.THUMBNAIL_URL}$thumbnail"
}
