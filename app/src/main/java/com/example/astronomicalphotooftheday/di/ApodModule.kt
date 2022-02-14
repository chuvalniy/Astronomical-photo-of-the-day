package com.example.astronomicalphotooftheday.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.astronomicalphotooftheday.core.utils.Constants.BASE_URL
import com.example.astronomicalphotooftheday.data.local.ApodDatabase
import com.example.astronomicalphotooftheday.data.remote.ApodApi
import com.example.astronomicalphotooftheday.data.repository.ApodRepositoryImpl
import com.example.astronomicalphotooftheday.domain.repository.ApodRepository
import com.example.astronomicalphotooftheday.domain.use_case.ApodUseCases
import com.example.astronomicalphotooftheday.domain.use_case.GetRandomApods
import com.example.astronomicalphotooftheday.domain.use_case.GetTodayApod
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApodModule {

    @Provides
    @Singleton
    fun provideApodUseCases(repository: ApodRepository): ApodUseCases {
        return ApodUseCases(
            getTodayApod = GetTodayApod(repository),
            getRandomApods = GetRandomApods(repository),
        )
    }

    @Provides
    @Singleton
    fun provideApodDatabase(application: Application): RoomDatabase {
        return Room.databaseBuilder(
            application,
            RoomDatabase::class.java,
            "apod_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideApodApi(): ApodApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApodRepository(api: ApodApi, db: ApodDatabase): ApodRepository {
        return ApodRepositoryImpl(api, db.dao)
    }
}