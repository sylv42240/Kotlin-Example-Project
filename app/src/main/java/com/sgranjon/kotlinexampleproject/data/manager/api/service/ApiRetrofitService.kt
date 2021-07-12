package com.sgranjon.kotlinexampleproject.data.manager.api.service

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import io.reactivex.Single
import retrofit2.http.GET

interface ApiRetrofitService {
    @GET("character")
    fun getAllCharacters(): Single<CharacterListResultRemoteEntity>
}