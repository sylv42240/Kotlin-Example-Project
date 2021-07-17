package com.sgranjon.kotlinexampleproject.data

import androidx.test.core.app.ApplicationProvider
import com.sgranjon.kotlinexampleproject.data.exception.RequestFailException
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManagerImpl
import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
import java.io.IOException
import javax.inject.Inject
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ApiManagerTests {

    @Inject
    lateinit var apiManager: ApiManager

    @Inject
    lateinit var retrofitService: ApiRetrofitService

    @Before
    fun setUp() {
        DaggerTestComponent.factory().create(ApplicationProvider.getApplicationContext())
            .inject(this)
        apiManager = ApiManagerImpl(retrofitService)
    }

    @Test
    fun getAllCharactersTest() = runBlocking<Unit> {
        apiManager.getAllCharacters(1).apply {
            assertThat(this.results).hasSize(20)
            assertThat(this.results.first().name).isEqualTo("Rick Sanchez")
        }
    }

    @Test
    fun getEpisodeFromCharacterTest() = runBlocking<Unit> {
        apiManager.getCharacterEpisode("1").apply {
            assertThat(this.name).isEqualTo("Pilot")
        }
    }

    @Test
    fun getEpisodesFromCharacterTest() = runBlocking<Unit> {
        apiManager.getCharacterEpisodeList("1,2,3").apply {
            assertThat(this).hasSize(3)
            assertThat(this.first().name).isEqualTo("Pilot")
        }
    }

    @Test
    fun getEpisodesFromCharacterErrorTest() = runBlocking<Unit> {
        try {
            apiManager.getCharacterEpisodeList("---")
        } catch (e: IOException) {
            assertThat(e).isInstanceOf(RequestFailException::class.java)
        }
    }

    @Test
    fun getAllEpisodesTest() = runBlocking<Unit> {
        apiManager.getAllEpisodes(1).apply {
            assertThat(this.results).hasSize(20)
            assertThat(this.results.first().name).isEqualTo("Pilot")
        }
    }

    @Test
    fun getAllLocationsTest() = runBlocking<Unit> {
        apiManager.getAllLocations(1).apply {
            assertThat(this.results).hasSize(20)
            assertThat(this.results.first().name).isEqualTo("Earth (C-137)")
        }
    }

}