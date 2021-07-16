package com.sgranjon.kotlinexampleproject.data.business

import androidx.paging.PagingData
import com.sgranjon.kotlinexampleproject.data.business.pager.CharacterPagingSource
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.exception.CharacterNotFoundException
import com.sgranjon.kotlinexampleproject.data.extensions.createFlowFromPagingSource
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.mapper.db.CharacterDBEntityDataMapper
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Reusable
class CharacterBusinessHelper @Inject constructor(
    private val dbManager: DbManager,
    private val characterDBEntityDataMapper: CharacterDBEntityDataMapper,
    private val characterPagingSource: CharacterPagingSource
) {

    fun retrieveCharacterList(): Flow<PagingData<CharacterEntity>> {
        return createFlowFromPagingSource(characterPagingSource)
    }

    fun retrieveCharacterById(id: Int): Flow<CharacterEntity> {
        return flow {
            emit(dbManager.getCharacterById(id)?.let {
                characterDBEntityDataMapper.transformDBToEntity(
                    it
                )
            } ?: throw CharacterNotFoundException())
        }
    }
}