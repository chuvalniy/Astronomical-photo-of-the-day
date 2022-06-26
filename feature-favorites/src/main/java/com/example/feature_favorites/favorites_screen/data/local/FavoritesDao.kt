package com.example.feature_favorites.favorites_screen.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorite_photos_db")
    fun getPhotos(): Flow<List<PhotoEntity>>

    @Delete
    suspend fun deletePhoto(photoEntity: PhotoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photoEntity: PhotoEntity)
}