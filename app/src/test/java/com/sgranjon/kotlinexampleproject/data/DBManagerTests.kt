package com.sgranjon.kotlinexampleproject.data

import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity
import com.sgranjon.kotlinexampleproject.data.manager.db.AppDatabase
import com.sgranjon.kotlinexampleproject.data.manager.db.DBManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DBManagerImpl
import com.sgranjon.kotlinexampleproject.data.manager.db.dao.CharacterDao
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class DBManagerTests {

    private lateinit var dbManager: DBManager

    private lateinit var characterDao: CharacterDao

    @Before
    fun setUp() {
        val appDatabase = Mockito.mock(AppDatabase::class.java)
        characterDao = Mockito.mock(CharacterDao::class.java)
        `when`(appDatabase.characterDao()).thenReturn(characterDao)

        dbManager = DBManagerImpl(appDatabase)
    }

    @Test
    fun saveCharacterListTest() = runBlocking {
        val list = emptyList<CharacterDBEntity>()
        dbManager.saveCharacterList(list)

        verify(characterDao).insertAll(list)
    }

    @Test
    fun getCharacterByIdTest() = runBlocking<Unit> {
        val id = 1
        dbManager.getCharacterById(id)

        verify(characterDao).getById(id)
    }

    @Test
    fun getCharacterListTest() = runBlocking<Unit> {
        dbManager.getAllCharacters()

        verify(characterDao).getAll()
    }

    @Test
    fun deleteCharacterListTest() = runBlocking {
        dbManager.deleteCharacterList()

        verify(characterDao).deleteAll()
    }
}