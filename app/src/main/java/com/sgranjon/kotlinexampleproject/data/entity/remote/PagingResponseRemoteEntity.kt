package com.sgranjon.kotlinexampleproject.data.entity.remote

import com.google.gson.annotations.SerializedName

data class PagingResponseRemoteEntity<T>(
    @SerializedName("info")
    val info: PagingRemoteEntity,
    @SerializedName("results")
    val results: List<T>
)
