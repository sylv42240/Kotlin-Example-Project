package com.sgranjon.kotlinexampleproject.data.mapper.remote

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.CharacterRemoteEntity
import com.sgranjon.kotlinexampleproject.data.exception.NoMappingAvailableException
import com.sgranjon.kotlinexampleproject.data.mapper.base.RemoteMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class CharacterRemoteEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : RemoteMapper<CharacterRemoteEntity, CharacterEntity>() {

    override fun transformEntityToRemote(input: CharacterEntity): CharacterRemoteEntity {
        throw NoMappingAvailableException()
    }

    override fun transformRemoteToEntity(input: CharacterRemoteEntity): CharacterEntity =
        CharacterEntity(
            id = input.id,
            name = input.name,
            status = input.status,
            species = input.species,
            type = input.type,
            gender = input.gender,
            image = input.image
        )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_CHARACTER, "error", error)
    }
}
