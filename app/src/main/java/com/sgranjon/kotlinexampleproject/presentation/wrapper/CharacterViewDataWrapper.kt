package com.sgranjon.kotlinexampleproject.presentation.wrapper

import com.sgranjon.kotlinexampleproject.data.model.Character

class CharacterViewDataWrapper(private val character: Character) {
    fun getName() = character.name
    fun getGender() = character.gender
    fun getImageUrl() = character.image
    fun getStatus() = character.status
    fun getSpecies() = character.species
    fun getType() = character.type
}