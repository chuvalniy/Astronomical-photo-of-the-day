package com.example.feature_favorites.favorites_screen.data.mapper

import com.example.feature_favorites.favorites_screen.data.local.PhotoEntity
import com.example.feature_favorites.favorites_screen.domain.model.Photo

fun PhotoEntity.toPhotoDomain(): Photo {
    return Photo(
        date = date,
        explanation = explanation,
        hdurl = hdurl,
        title = title,
        url = url
    )
}

fun Photo.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        date = date,
        explanation = explanation,
        hdurl = hdurl,
        title = title,
        url = url
    )
}