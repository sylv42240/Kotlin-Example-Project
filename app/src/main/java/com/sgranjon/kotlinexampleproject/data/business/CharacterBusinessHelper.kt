package com.sgranjon.kotlinexampleproject.data.business

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.observable
import com.sgranjon.kotlinexampleproject.data.business.pager.CharacterPagingSource
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.entity.local.EpisodeEntity
import com.sgranjon.kotlinexampleproject.data.exception.CharacterNotFoundException
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.mapper.db.CharacterDBEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.mapper.remote.EpisodeRemoteEntityDataMapper
import dagger.Reusable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Reusable
class CharacterBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DbManager,
    private val characterDBEntityDataMapper: CharacterDBEntityDataMapper,
    private val episodeRemoteEntityDataMapper: EpisodeRemoteEntityDataMapper,
    private val characterPagingSource: CharacterPagingSource
) {

    @ExperimentalCoroutinesApi
    fun retrieveCharacterList(): Observable<PagingData<CharacterEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { characterPagingSource }
        ).observable
    }

    fun retrieveCharacterById(id: Int): CharacterEntity {
        return dbManager.getCharacterById(id)?.let {
            characterDBEntityDataMapper.transformDBToEntity(
                it
            )
        } ?: throw CharacterNotFoundException()
    }

    fun retrieveCharacterEpisodeList(id: Int): Single<List<EpisodeEntity>> {
        val character = dbManager.getCharacterById(id) ?: throw CharacterNotFoundException()
        val episodeIds = character.episodeList.joinToString { it.split("/").last() }
        return if (character.episodeList.size == 1) {
            apiManager.getCharacterEpisode(episodeIds).map {
                listOf(episodeRemoteEntityDataMapper.transformRemoteToEntity(it))
            }
        } else {
            apiManager.getCharacterEpisodeList(episodeIds).map {
                episodeRemoteEntityDataMapper.transformRemoteEntityList(it)
            }
        }
    }
}