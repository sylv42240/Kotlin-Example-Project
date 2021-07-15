package com.sgranjon.kotlinexampleproject.data.entity.remote

import com.google.gson.annotations.SerializedName

data class PagingRemoteEntity(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?
)
