package com.example.feature_favorites.favorites_screen.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.feature_favorites.favorites_screen.data.local.FavoritesDatabase
import com.example.feature_favorites.favorites_screen.data.repository.FavoritesRepositoryImpl
import com.example.feature_favorites.favorites_screen.domain.repository.FavoritesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideFavoritesDatabase(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application,
            FavoritesDatabase::class.java,
            "favorite_photos_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavoritesRepository(database: FavoritesDatabase): FavoritesRepository {
        return FavoritesRepositoryImpl(database)
    }
}