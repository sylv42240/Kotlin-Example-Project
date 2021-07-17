package com.sgranjon.kotlinexampleproject.data

import androidx.test.core.app.ApplicationProvider
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManagerImpl
import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
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
    fun getAllCharacters() = runBlocking<Unit> {
        val page = 1
        apiManager.getAllCharacters(page).apply {
            assertThat(this.results).hasSize(20)
        }
    }

}