package com.example.feature_favorites.data.local

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.feature_favorites.favorites_screen.data.local.FavoritesDao
import com.example.feature_favorites.favorites_screen.data.local.FavoritesDatabase
import com.example.feature_favorites.favorites_screen.data.local.PhotoEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@RunWith(AndroidJUnit4::class)
@SmallTest
class FavoritesDaoTest {

    private lateinit var database: FavoritesDatabase
    private lateinit var dao: FavoritesDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavoritesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertPhoto() = runBlocking {
        val photo = PhotoEntity(
            date = UUID.randomUUID().toString(),
            explanation = UUID.randomUUID().toString(),
            hdurl = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString(),
            url = UUID.randomUUID().toString()
        )
        dao.insertPhoto(photo)

        val allPhotos = dao.getPhotos().first()

        assertThat(allPhotos).contains(photo)
    }

    @Test
    fun deletePhoto() = runBlocking {
        val photo = PhotoEntity(
            date = UUID.randomUUID().toString(),
            explanation = UUID.randomUUID().toString(),
            hdurl = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString(),
            url = UUID.randomUUID().toString()
        )
        dao.insertPhoto(photo)
        dao.deletePhoto(photo)

        val allPhotos = dao.getPhotos().first()

        assertThat(allPhotos).doesNotContain(photo)
    }
}