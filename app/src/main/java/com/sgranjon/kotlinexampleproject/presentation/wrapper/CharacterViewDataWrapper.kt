package com.sgranjon.kotlinexampleproject.presentation.wrapper

import android.content.Context
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.data.model.Character
import com.sgranjon.kotlinexampleproject.data.model.CharacterGender
import com.sgranjon.kotlinexampleproject.data.model.CharacterStatus

class CharacterViewDataWrapper(private val character: Character) {
    fun getName() = character.name

    fun getImageUrl() = character.image

    fun getSpecies() = character.species

    fun getType() = character.type

    fun getGender(context: Context) = context.getString(
        when (character.gender) {
            CharacterGender.FEMALE -> R.string.character_gender_female
            CharacterGender.MALE -> R.string.character_gender_male
            CharacterGender.GENDERLESS -> R.string.character_gender_genderless
            CharacterGender.UNKNOWN -> R.string.character_gender_unknown
        }
    )

    fun getStatus(context: Context) = context.getString(
        when (character.status) {
            CharacterStatus.ALIVE -> R.string.character_status_alive
            CharacterStatus.DEAD -> R.string.character_status_dead
            CharacterStatus.UNKNOWN -> R.string.character_status_unknown
        }
    )
}