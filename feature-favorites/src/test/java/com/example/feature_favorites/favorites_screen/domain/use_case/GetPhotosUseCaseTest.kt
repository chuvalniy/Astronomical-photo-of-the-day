package com.example.feature_favorites.favorites_screen.domain.use_case

import com.example.feature_favorites.favorites_screen.data.repository.FakeRepository
import com.example.feature_favorites.favorites_screen.domain.model.Photo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.util.*

class GetPhotosUseCaseTest {

    private lateinit var getPhotosUseCase: GetPhotosUseCase
    private lateinit var fakeRepository: FakeRepository

    @Before
    fun setup() {
        fakeRepository = FakeRepository()
        getPhotosUseCase = GetPhotosUseCase(fakeRepository)
    }

    @Test
    fun getPhotos() = runBlocking {
        val expectedResult = mutableListOf<Photo>()

        val photo = Photo(
            date = UUID.randomUUID().toString(),
            explanation = UUID.randomUUID().toString(),
            hdurl = UUID.randomUUID().toString(),
            title = UUID.randomUUID().toString(),
            url = UUID.randomUUID().toString()
        )

        expectedResult.add(photo)
        fakeRepository.insertPhoto(photo)

        val photos = getPhotosUseCase().first()

        assertThat(photos).isEqualTo(expectedResult)
    }
}