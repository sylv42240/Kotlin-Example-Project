package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.PagingResponseRemoteEntity

interface ApiManager {
    suspend fun getAllCharacters(page: Int): PagingResponseRemoteEntity<CharacterRemoteEntity>
    suspend fun getCharacterEpisodeList(ids: String): List<EpisodeRemoteEntity>
    suspend fun getCharacterEpisode(id: String): EpisodeRemoteEntity
}