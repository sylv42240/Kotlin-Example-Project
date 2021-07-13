package com.sgranjon.kotlinexampleproject.data.mapper.local

import com.sgranjon.kotlinexampleproject.data.model.CharacterStatus
import dagger.Reusable
import javax.inject.Inject

private const val STATUS_ALIVE = "Alive"
private const val STATUS_DEAD = "Dead"

@Reusable
class CharacterStatusDataMapper @Inject constructor() {

    fun transformStringToCharacterStatus(input: String): CharacterStatus =
        when (input) {
            STATUS_ALIVE -> CharacterStatus.ALIVE
            STATUS_DEAD -> CharacterStatus.DEAD
            else -> CharacterStatus.UNKNOWN
        }
}