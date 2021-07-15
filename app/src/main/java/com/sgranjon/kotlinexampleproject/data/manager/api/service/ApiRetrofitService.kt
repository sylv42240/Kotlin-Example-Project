package com.sgranjon.kotlinexampleproject.data.manager.api.service

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRetrofitService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): CharacterListResultRemoteEntity

    @GET("episode/{ids}")
    suspend fun getCharacterEpisodeList(@Path("ids") ids: String): List<EpisodeRemoteEntity>

    @GET("episode/{id}")
    suspend fun getCharacterEpisode(@Path("id") id: String): EpisodeRemoteEntity
}