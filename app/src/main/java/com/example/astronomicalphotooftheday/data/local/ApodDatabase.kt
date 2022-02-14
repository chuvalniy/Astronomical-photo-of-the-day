package com.example.astronomicalphotooftheday.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.astronomicalphotooftheday.data.local.entity.ApodEntity

@Database(
    entities = [ApodEntity::class],
    version = 1
)
abstract class ApodDatabase: RoomDatabase() {

    abstract val dao: ApodDao
}