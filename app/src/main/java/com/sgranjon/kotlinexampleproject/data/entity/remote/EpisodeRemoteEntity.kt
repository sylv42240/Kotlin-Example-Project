package com.sgranjon.kotlinexampleproject.data.entity.remote

import com.google.gson.annotations.SerializedName

data class EpisodeRemoteEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("air_date")
    val date: String,
    @SerializedName("episode")
    val number: String,
    @SerializedName("characters")
    val characterList: List<String>
)
