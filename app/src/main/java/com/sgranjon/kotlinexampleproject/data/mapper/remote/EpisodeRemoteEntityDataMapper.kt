package com.sgranjon.kotlinexampleproject.data.mapper.remote

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.local.EpisodeEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.EpisodeRemoteEntity
import com.sgranjon.kotlinexampleproject.data.exception.NoMappingAvailableException
import com.sgranjon.kotlinexampleproject.data.mapper.base.RemoteMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class EpisodeRemoteEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : RemoteMapper<EpisodeRemoteEntity, EpisodeEntity>() {

    override fun transformEntityToRemote(input: EpisodeEntity): EpisodeRemoteEntity {
        throw NoMappingAvailableException()
    }

    override fun transformRemoteToEntity(input: EpisodeRemoteEntity): EpisodeEntity =
        EpisodeEntity(
            id = input.id,
            name = input.name,
            date = input.date,
            number = input.number
        )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_EPISODE, "error", error)
    }
}
