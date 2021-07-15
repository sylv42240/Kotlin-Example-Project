package com.sgranjon.kotlinexampleproject.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.business.CharacterBusinessHelper
import com.sgranjon.kotlinexampleproject.data.mapper.local.CharacterEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.mapper.local.EpisodeEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.model.Character
import com.sgranjon.kotlinexampleproject.data.model.Episode
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Reusable
class CharacterRepository @Inject constructor(
    private val characterBusinessHelper: CharacterBusinessHelper,
    private val characterEntityDataMapper: CharacterEntityDataMapper,
    private val episodeEntityDataMapper: EpisodeEntityDataMapper
) {
    @ExperimentalCoroutinesApi
    fun retrieveCharacterList(): Flow<PagingData<Character>> =
        characterBusinessHelper.retrieveCharacterList().map {
            it.map { character -> characterEntityDataMapper.transformEntityToModel(character) }
        }

    fun retrieveCharacterById(id: Int): Flow<Character> =
        characterBusinessHelper.retrieveCharacterById(id)
            .map { characterEntityDataMapper.transformEntityToModel(it) }


    fun retrieveCharacterEpisodeList(id: Int): Flow<List<Episode>> =
        characterBusinessHelper.retrieveCharacterEpisodeList(id).map {
            episodeEntityDataMapper.transformEntityList(it)
        }

}
