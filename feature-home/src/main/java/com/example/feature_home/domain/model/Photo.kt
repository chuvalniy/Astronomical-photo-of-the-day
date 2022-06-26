package com.example.feature_home.domain.model

import com.example.feature_favorites.favorites_screen.domain.model.Photo

data class Photo(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val title: String,
    val url: String?
) {
    fun toFavoritesPhoto(): Photo {
        return Photo(
            date = date,
            explanation = explanation,
            hdurl = hdurl,
            title = title,
            url = url
        )
    }
}
