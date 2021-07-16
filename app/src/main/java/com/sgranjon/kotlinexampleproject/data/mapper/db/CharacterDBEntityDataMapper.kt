package com.sgranjon.kotlinexampleproject.data.mapper.db

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.db.CharacterDBEntity
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.mapper.base.DBMapper
import dagger.Reusable
import javax.inject.Inject


@Reusable
class CharacterDBEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : DBMapper<CharacterDBEntity, CharacterEntity>() {

    override fun transformDBToEntity(input: CharacterDBEntity): CharacterEntity = CharacterEntity(
        id = input.id,
        name = input.name,
        status = input.status,
        species = input.species,
        type = input.type,
        gender = input.gender,
        image = input.image,
        origin = input.origin,
        episodeList = input.episodeList
    )

    override fun transformEntityToDB(input: CharacterEntity): CharacterDBEntity = CharacterDBEntity(
        id = input.id,
        name = input.name,
        status = input.status,
        species = input.species,
        type = input.type,
        gender = input.gender,
        image = input.image,
        origin = input.origin,
        episodeList = input.episodeList
    )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.DB_MAPPER_CHARACTER, "error", error)
    }

}