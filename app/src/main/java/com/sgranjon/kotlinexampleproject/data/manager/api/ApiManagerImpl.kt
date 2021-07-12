package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
import io.reactivex.Single
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(private val apiRetrofitService: ApiRetrofitService) :
    ApiManager {
    override fun getAllCharacters(): Single<CharacterListResultRemoteEntity> {
        return apiRetrofitService.getAllCharacters()
    }

}