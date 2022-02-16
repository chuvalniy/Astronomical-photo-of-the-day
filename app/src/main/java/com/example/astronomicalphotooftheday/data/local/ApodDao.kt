package com.example.astronomicalphotooftheday.data.local

import androidx.room.*
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity

@Dao
interface ApodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApods(apods: List<ApodEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApod(apod: ApodEntity)

    @Delete
    suspend fun deleteApod(apod: ApodEntity)

    @Query("SELECT * FROM apod_db")
    suspend fun getAll(): List<ApodEntity>

    @Query("SELECT * FROM apod_db WHERE id = :id")
    suspend fun getItemById(id: Int): ApodEntity

    @Query("DELETE FROM apod_db")
    suspend fun deleteAll()
}