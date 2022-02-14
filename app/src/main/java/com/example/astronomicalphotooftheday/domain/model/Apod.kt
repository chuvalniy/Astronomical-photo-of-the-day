package com.example.astronomicalphotooftheday.domain.model

import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity

data class Apod(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val title: String,
    val url: String?
) {
    fun toApodEntity(): ApodEntity {
        return ApodEntity(
            date = date,
            explanation = explanation,
            hdurl = hdurl,
            title = title,
            url = url
        )
    }
}
