package com.sgranjon.kotlinexampleproject.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CharacterListResultRemoteEntity(
    @SerializedName("info")
    val info: PagingRemoteEntity,
    @SerializedName("results")
    val results: List<CharacterRemoteEntity>
)
