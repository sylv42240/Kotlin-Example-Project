package com.sgranjon.kotlinexampleproject.data.manager.db

import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity


interface DBManager {
    suspend fun getAllCharacters(): List<CharacterDBEntity>
    suspend fun getCharacterById(id: Int): CharacterDBEntity?
    suspend fun saveCharacterList(characters: List<CharacterDBEntity>)
    suspend fun deleteCharacterList()
}