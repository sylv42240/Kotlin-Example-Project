package com.sgranjon.kotlinexampleproject.data.manager.api.service

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRetrofitService {
    @GET("character")
    fun getAllCharacters(@Query("page") page: Int): Single<CharacterListResultRemoteEntity>

    @GET("episode/{ids}")
    fun getCharacterEpisodeList(@Path("ids") ids: String): Single<List<EpisodeRemoteEntity>>

    @GET("episode/{id}")
    fun getCharacterEpisode(@Path("id") id: String): Single<EpisodeRemoteEntity>
}