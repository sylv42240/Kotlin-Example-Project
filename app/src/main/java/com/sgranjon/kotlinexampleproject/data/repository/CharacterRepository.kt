package com.sgranjon.kotlinexampleproject.data.repository

import com.sgranjon.kotlinexampleproject.data.business.CharacterBusinessHelper
import com.sgranjon.kotlinexampleproject.data.mapper.local.CharacterEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.model.Character
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class CharacterRepository @Inject constructor(
    private val characterBusinessHelper: CharacterBusinessHelper,
    private val characterEntityDataMapper: CharacterEntityDataMapper
) {
    fun retrieveCharacterList(): Single<List<Character>> = Single.defer {
        characterBusinessHelper.retrieveCharacterList().map {
            characterEntityDataMapper.transformEntityList(it)
        }
    }

    fun retrieveCharacterById(id: Int): Single<Character> = Single.defer {
        Single.just(
            characterEntityDataMapper.transformEntityToModel(
                characterBusinessHelper.retrieveCharacterById(
                    id
                )
            )
        )
    }
}
