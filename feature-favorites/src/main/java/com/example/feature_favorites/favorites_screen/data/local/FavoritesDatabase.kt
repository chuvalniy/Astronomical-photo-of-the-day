package com.example.feature_favorites.favorites_screen.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PhotoEntity::class],
    version = 1
)
abstract class FavoritesDatabase : RoomDatabase() {
    abstract val dao: FavoritesDao
}