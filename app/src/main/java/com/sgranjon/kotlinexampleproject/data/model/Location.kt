package com.sgranjon.kotlinexampleproject.data.model

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residentList: List<String>
)
