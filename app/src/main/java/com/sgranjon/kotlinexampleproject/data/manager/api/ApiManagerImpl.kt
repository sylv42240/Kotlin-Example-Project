package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.LocationRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.PagingResponseRemoteEntity
import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(private val apiRetrofitService: ApiRetrofitService) :
    ApiManager {

    override suspend fun getAllCharacters(page: Int): PagingResponseRemoteEntity<CharacterRemoteEntity> {
        return apiRetrofitService.getAllCharacters(page)
    }

    override suspend fun getCharacterEpisodeList(ids: String): List<EpisodeRemoteEntity> {
        return apiRetrofitService.getCharacterEpisodeList(ids)
    }

    override suspend fun getCharacterEpisode(id: String): EpisodeRemoteEntity {
        return apiRetrofitService.getCharacterEpisode(id)
    }

    override suspend fun getAllEpisodes(page: Int): PagingResponseRemoteEntity<EpisodeRemoteEntity> {
        return apiRetrofitService.getAllEpisodes(page)
    }

    override suspend fun getAllLocations(page: Int): PagingResponseRemoteEntity<LocationRemoteEntity> {
        return apiRetrofitService.getAllLocations(page)
    }

}