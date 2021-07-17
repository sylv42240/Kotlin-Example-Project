package com.sgranjon.kotlinexampleproject.data.db

import android.os.Build
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity
import com.sgranjon.kotlinexampleproject.data.manager.db.AppDatabase
import com.sgranjon.kotlinexampleproject.data.manager.db.dao.CharacterDao
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class DatabaseCharacterTests {

    private lateinit var characterDao: CharacterDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        characterDao = appDatabase.characterDao()
    }

    @After
    fun after() {
        appDatabase.close()
    }

    @Test
    fun getAllCharactersEmptyTest() = runBlocking<Unit> {
        assertThat(characterDao.getAll()).hasSize(0)
    }

    @Test
    fun saveAllCharactersTest() = runBlocking<Unit> {
        characterDao.insertAll(generateCharacterList())

        assertThat(characterDao.getAll()).hasSize(3)
    }


    @Test
    fun getCharacterByIdExistingTest() = runBlocking<Unit> {
        characterDao.insertAll(generateCharacterList())

        val characterDBEntity = characterDao.getById(1)
        assertThat(characterDBEntity).isNotNull
        assertThat(characterDBEntity?.name).isEqualTo("Rick")
    }


    @Test
    fun getCharacterByIdNotExistingTest() = runBlocking {
        characterDao.insertAll(generateCharacterList())

        val press = characterDao.getById(4)
        assertThat(press).isNull()
    }


    @Test
    fun deleteAllCharactersTest() = runBlocking<Unit> {
        characterDao.insertAll(generateCharacterList())

        assertThat(characterDao.getAll()).hasSize(3)

        characterDao.deleteAll()

        assertThat(characterDao.getAll()).hasSize(0)
    }


    private fun generateCharacterList() = listOf(
        CharacterDBEntity(1, "Rick", "", "", "", "", "", "", listOf()),
        CharacterDBEntity(2, "Morty", "", "", "", "", "", "", listOf()),
        CharacterDBEntity(3, "Beth", "", "", "", "", "", "", listOf())
    )

}