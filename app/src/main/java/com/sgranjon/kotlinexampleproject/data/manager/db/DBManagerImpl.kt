package com.sgranjon.kotlinexampleproject.data.manager.db


import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity
import com.sgranjon.kotlinexampleproject.data.manager.db.dao.CharacterDao
import javax.inject.Inject

class DBManagerImpl @Inject constructor(appDatabase: AppDatabase) : DBManager {

    private val characterDao: CharacterDao = appDatabase.characterDao()

    override suspend fun getAllCharacters(): List<CharacterDBEntity> {
        return characterDao.getAll()
    }

    override suspend fun getCharacterById(id: Int): CharacterDBEntity? {
        return characterDao.getById(id)
    }

    override suspend fun saveCharacterList(characters: List<CharacterDBEntity>) {
        characterDao.insertAll(characters)
    }

    override suspend fun deleteCharacterList() {
        characterDao.deleteAll()
    }

}