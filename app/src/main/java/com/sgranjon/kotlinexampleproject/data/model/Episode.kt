package com.sgranjon.kotlinexampleproject.data.model

data class Episode(
    val id: Int,
    val name: String,
    val date: String,
    val number: String,
    val characterList: List<String>
)
