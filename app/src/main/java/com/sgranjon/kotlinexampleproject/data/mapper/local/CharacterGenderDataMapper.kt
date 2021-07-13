package com.sgranjon.kotlinexampleproject.data.mapper.local

import com.sgranjon.kotlinexampleproject.data.model.CharacterGender
import dagger.Reusable
import javax.inject.Inject

private const val GENDER_FEMALE = "Female"
private const val GENDER_MALE = "Male"
private const val GENDER_GENDERLESS = "Genderless"

@Reusable
class CharacterGenderDataMapper @Inject constructor() {

    fun transformStringToCharacterGender(input: String): CharacterGender =
        when (input) {
            GENDER_FEMALE -> CharacterGender.FEMALE
            GENDER_MALE -> CharacterGender.MALE
            GENDER_GENDERLESS -> CharacterGender.GENDERLESS
            else -> CharacterGender.UNKNOWN
        }
}