package com.sgranjon.kotlinexampleproject.data.manager.db

import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity


interface DbManager {
    fun getAllCharacters(): List<CharacterDBEntity>
    fun getCharacterById(id: Int): CharacterDBEntity?
    fun saveCharacterList(characters: List<CharacterDBEntity>)
    fun deleteCharacterList()
}