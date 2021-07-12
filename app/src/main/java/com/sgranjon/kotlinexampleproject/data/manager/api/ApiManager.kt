package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterListResultRemoteEntity
import io.reactivex.Single

interface ApiManager {
    fun getAllCharacters(): Single<CharacterListResultRemoteEntity>
}