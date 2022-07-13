package com.example.feature_favorites.favorites_screen.domain.use_case

import com.example.feature_favorites.favorites_screen.data.repository.FakeRepository
import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class InsertPhotoUseCaseTest {

    private lateinit var insertPhotoUseCase: InsertPhotoUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        insertPhotoUseCase = InsertPhotoUseCase(fakeRepository)
    }

    @Test
    fun insertPhoto() = runBlocking {
        val photo = Photo(
            date = UUID.randomUUID().toString(),
            explanation = UUID.randomUUID().toString(),
            hdurl = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString(),
            url = UUID.randomUUID().toString()
        )

        insertPhotoUseCase(photo = photo)

        val photos = fakeRepository.getPhotos().first()

        assertThat(photos).contains(photo)
    }
}