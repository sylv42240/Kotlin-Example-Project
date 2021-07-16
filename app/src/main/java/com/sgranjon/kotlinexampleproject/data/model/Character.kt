package com.sgranjon.kotlinexampleproject.data.model

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val image: String,
    val origin: String,
    val episodeList: List<String>
)
