package com.sgranjon.kotlinexampleproject.data.mapper.local

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.exception.NoMappingAvailableException
import com.sgranjon.kotlinexampleproject.data.mapper.base.ModelMapper
import com.sgranjon.kotlinexampleproject.data.model.Character
import dagger.Reusable
import javax.inject.Inject


@Reusable
class CharacterEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : ModelMapper<Character, CharacterEntity>() {

    override fun transformModelToEntity(input: Character): CharacterEntity {
        throw NoMappingAvailableException()
    }

    override fun transformEntityToModel(input: CharacterEntity): Character = Character(
        id = input.id,
        name = input.name,
        status = input.status,
        species = input.species,
        type = input.type,
        gender = input.gender,
        image = input.image
    )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.MODEL_MAPPER_CHARACTER, "error", error)
    }
}