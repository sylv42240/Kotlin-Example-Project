package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import io.reactivex.Single

interface ApiManager {
    fun getAllCharacters(page: Int): Single<CharacterListResultRemoteEntity>
    fun getCharacterEpisodeList(ids: String): Single<List<EpisodeRemoteEntity>>
    fun getCharacterEpisode(id: String): Single<EpisodeRemoteEntity>
}