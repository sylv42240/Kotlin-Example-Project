package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(private val apiRetrofitService: ApiRetrofitService) :
    ApiManager {

    override suspend fun getAllCharacters(page: Int): CharacterListResultRemoteEntity {
        return apiRetrofitService.getAllCharacters(page)
    }

    override suspend fun getCharacterEpisodeList(ids: String): List<EpisodeRemoteEntity> {
        return apiRetrofitService.getCharacterEpisodeList(ids)
    }

    override suspend fun getCharacterEpisode(id: String): EpisodeRemoteEntity {
        return apiRetrofitService.getCharacterEpisode(id)
    }

}