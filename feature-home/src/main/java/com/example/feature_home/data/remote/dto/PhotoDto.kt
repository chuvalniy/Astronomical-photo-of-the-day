package com.example.feature_home.data.remote.dto

import com.example.feature_home.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String?,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("service_version")
    val serviceVersion: String,
    val title: String,
    val url: String?
) {
    fun toPhotoDomain(): Photo {
        return Photo(
            date = date,
            explanation = explanation,
            hdurl = hdurl,
            title = title,
            url = url
        )
    }
}
