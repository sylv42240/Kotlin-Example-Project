package com.sgranjon.kotlinexampleproject.presentation.wrapper

import android.content.Context
import androidx.core.content.ContextCompat
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.data.model.Character
import com.sgranjon.kotlinexampleproject.data.model.CharacterGender
import com.sgranjon.kotlinexampleproject.data.model.CharacterStatus

class CharacterViewDataWrapper(private val character: Character) {

    fun getId() = character.id

    fun getName() = character.name

    fun getImageUrl() = character.image

    fun getSpecies(context: Context) = if (character.species == "unknown") {
        context.getString(R.string.character_origin_and_species_unknown_label)
    } else {
        character.species
    }

    fun getOrigin(context: Context) = if (character.origin == "unknown") {
        context.getString(R.string.character_origin_and_species_unknown_label)
    } else {
        character.origin
    }

    fun getEpisodeCountText(context: Context) = context.resources.getQuantityString(
        R.plurals.character_episode_count,
        character.episodeList.size,
        character.episodeList.size
    )

    fun getGender(context: Context) = context.getString(
        when (character.gender) {
            CharacterGender.FEMALE -> R.string.character_gender_female
            CharacterGender.MALE -> R.string.character_gender_male
            CharacterGender.GENDERLESS -> R.string.character_gender_genderless
            CharacterGender.UNKNOWN -> R.string.character_gender_unknown
        }
    )

    fun getGenderIcon(context: Context) = ContextCompat.getDrawable(
        context,
        when (character.gender) {
            CharacterGender.FEMALE -> R.drawable.ic_gender_female
            CharacterGender.MALE -> R.drawable.ic_gender_male
            CharacterGender.GENDERLESS -> R.drawable.ic_gender_genderless
            CharacterGender.UNKNOWN -> R.drawable.ic_unknown
        }
    )

    fun getStatus(context: Context) = context.getString(
        when (character.status) {
            CharacterStatus.ALIVE -> R.string.character_status_alive
            CharacterStatus.DEAD -> R.string.character_status_dead
            CharacterStatus.UNKNOWN -> R.string.character_status_unknown
        }
    )

    fun getStatusIcon(context: Context) = ContextCompat.getDrawable(
        context,
        when (character.status) {
            CharacterStatus.ALIVE -> R.drawable.ic_status_alive
            CharacterStatus.DEAD -> R.drawable.ic_status_dead
            CharacterStatus.UNKNOWN -> R.drawable.ic_unknown
        }
    )
}