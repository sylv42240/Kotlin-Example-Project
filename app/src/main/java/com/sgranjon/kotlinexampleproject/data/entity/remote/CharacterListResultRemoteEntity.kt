package com.sgranjon.kotlinexampleproject.data.entity.remote

import com.google.gson.annotations.SerializedName

data class CharacterListResultRemoteEntity(
    @SerializedName("results")
    val results: List<CharacterRemoteEntity>
)
