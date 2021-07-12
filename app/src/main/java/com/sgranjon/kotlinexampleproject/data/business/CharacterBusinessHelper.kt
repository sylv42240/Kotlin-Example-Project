package com.sgranjon.kotlinexampleproject.data.business

import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.exception.CharacterNotFoundException
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.mapper.db.CharacterDBEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.mapper.remote.CharacterRemoteEntityDataMapper
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class CharacterBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DbManager,
    private val characterRemoteEntityDataMapper: CharacterRemoteEntityDataMapper,
    private val characterDBEntityDataMapper: CharacterDBEntityDataMapper
) {
    fun retrieveCharacterList(): Single<List<CharacterEntity>> {
        return apiManager.getAllCharacters().map {
            characterRemoteEntityDataMapper.transformRemoteEntityList(it.results)
        }.doOnSuccess {
            dbManager.saveCharacterList(characterDBEntityDataMapper.transformEntityList(it))
        }.onErrorResumeNext {
            Single.just(retrieveCharacterListFromDB())
            throw it
        }
    }

    fun retrieveCharacterById(id: Int): CharacterEntity {
        return dbManager.getCharacterById(id)?.let {
            characterDBEntityDataMapper.transformDBToEntity(
                it
            )
        } ?: throw CharacterNotFoundException()
    }

    private fun retrieveCharacterListFromDB(): List<CharacterEntity> {
        val characterList = dbManager.getAllCharacters()
        if (characterList.isEmpty()) {
            throw CharacterNotFoundException()
        } else {
            return characterDBEntityDataMapper.transformDBEntityList(characterList)
        }
    }
}