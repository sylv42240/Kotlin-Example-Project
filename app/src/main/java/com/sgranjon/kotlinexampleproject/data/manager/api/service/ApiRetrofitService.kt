package com.sgranjon.kotlinexampleproject.data.manager.api.service

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.PagingResponseRemoteEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRetrofitService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): PagingResponseRemoteEntity<CharacterRemoteEntity>

    @GET("episode/{ids}")
    suspend fun getCharacterEpisodeList(@Path("ids") ids: String): List<EpisodeRemoteEntity>

    @GET("episode/{id}")
    suspend fun getCharacterEpisode(@Path("id") id: String): EpisodeRemoteEntity

    @GET("episode")
    suspend fun getAllEpisodes(@Query("page") page: Int): PagingResponseRemoteEntity<EpisodeRemoteEntity>
}