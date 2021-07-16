package com.sgranjon.kotlinexampleproject.data.entity.local

data class LocationEntity(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residentList: List<String>
)
