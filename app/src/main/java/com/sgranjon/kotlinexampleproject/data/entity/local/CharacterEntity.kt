package com.sgranjon.kotlinexampleproject.data.entity.local

data class CharacterEntity(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val origin: String,
    val episodeList: List<String>
)
