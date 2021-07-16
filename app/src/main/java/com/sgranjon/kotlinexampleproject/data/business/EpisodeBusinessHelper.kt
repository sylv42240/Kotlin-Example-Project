package com.sgranjon.kotlinexampleproject.data.business

import androidx.paging.PagingData
import com.sgranjon.kotlinexampleproject.data.business.pager.EpisodePagingSource
import com.sgranjon.kotlinexampleproject.data.entity.local.EpisodeEntity
import com.sgranjon.kotlinexampleproject.data.exception.CharacterNotFoundException
import com.sgranjon.kotlinexampleproject.data.extensions.createFlowFromPagingSource
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.mapper.remote.EpisodeRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val API_PAGE_SIZE = 20

@Reusable
class EpisodeBusinessHelper @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DbManager,
    private val episodeRemoteEntityDataMapper: EpisodeRemoteEntityDataMapper,
    private val episodePagingSource: EpisodePagingSource
) {

    fun retrieveEpisodeList(): Flow<PagingData<EpisodeEntity>> {
        return createFlowFromPagingSource(episodePagingSource)
    }

    fun retrieveCharacterEpisodeList(id: Int): Flow<List<EpisodeEntity>> {
        return flow {
            val character = dbManager.getCharacterById(id) ?: throw CharacterNotFoundException()
            val episodeIds = character.episodeList.joinToString { it.split("/").last() }
            val episodeList = mutableListOf<EpisodeEntity>()
            emit(episodeList)
            if (character.episodeList.size == 1) {
                episodeList.add(
                    episodeRemoteEntityDataMapper.transformRemoteToEntity(
                        apiManager.getCharacterEpisode(
                            episodeIds
                        )
                    )
                )
            } else {
                episodeList.addAll(
                    episodeRemoteEntityDataMapper.transformRemoteEntityList(
                        apiManager.getCharacterEpisodeList(episodeIds)
                    )
                )
            }
            emit(episodeList.toList())
        }
    }
}