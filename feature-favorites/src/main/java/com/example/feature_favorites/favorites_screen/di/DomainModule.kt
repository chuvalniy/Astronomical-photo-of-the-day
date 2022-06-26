package com.example.feature_favorites.favorites_screen.di

import com.example.feature_favorites.favorites_screen.domain.repository.FavoritesRepository
import com.example.feature_favorites.favorites_screen.domain.use_case.DeletePhotoUseCase
import com.example.feature_favorites.favorites_screen.domain.use_case.GetPhotosUseCase
import com.example.feature_favorites.favorites_screen.domain.use_case.InsertPhotoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetPhotosUseCase(repository: FavoritesRepository): GetPhotosUseCase {
        return GetPhotosUseCase(repository)
    }

    @Provides
    fun provideInsertPhotoUseCase(repository: FavoritesRepository): InsertPhotoUseCase {
        return InsertPhotoUseCase(repository)
    }

    @Provides
    fun provideDeletePhotoUseCase(repository: FavoritesRepository): DeletePhotoUseCase {
        return DeletePhotoUseCase(repository)
    }
}