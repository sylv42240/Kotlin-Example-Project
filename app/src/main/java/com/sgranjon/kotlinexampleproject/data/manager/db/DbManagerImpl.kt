package com.sgranjon.kotlinexampleproject.data.manager.db


import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity
import com.sgranjon.kotlinexampleproject.data.manager.db.dao.CharacterDao
import javax.inject.Inject

class DbManagerImpl @Inject constructor(appDatabase: AppDatabase) : DbManager {

    private val characterDao: CharacterDao = appDatabase.characterDao()

    override fun getAllCharacters(): List<CharacterDBEntity> {
        return characterDao.getAll()
    }

    override fun getCharacterById(id: Int): CharacterDBEntity? {
        return characterDao.getById(id)
    }

    override fun saveCharacterList(characters: List<CharacterDBEntity>) {
        characterDao.insertAll(characters)
    }

    override fun deleteCharacterList() {
        characterDao.deleteAll()
    }

}