package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity

interface ApiManager {
    suspend fun getAllCharacters(page: Int): CharacterListResultRemoteEntity
    suspend fun getCharacterEpisodeList(ids: String): List<EpisodeRemoteEntity>
    suspend fun getCharacterEpisode(id: String): EpisodeRemoteEntity
}