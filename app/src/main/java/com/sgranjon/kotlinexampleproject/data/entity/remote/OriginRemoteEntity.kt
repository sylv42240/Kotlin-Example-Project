package com.sgranjon.kotlinexampleproject.data.entity.remote

import com.google.gson.annotations.SerializedName

data class OriginRemoteEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
