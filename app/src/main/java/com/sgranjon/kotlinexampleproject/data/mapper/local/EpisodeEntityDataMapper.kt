package com.sgranjon.kotlinexampleproject.data.mapper.local

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.local.EpisodeEntity
import com.sgranjon.kotlinexampleproject.data.exception.NoMappingAvailableException
import com.sgranjon.kotlinexampleproject.data.mapper.base.ModelMapper
import com.sgranjon.kotlinexampleproject.data.model.Episode
import dagger.Reusable
import javax.inject.Inject


@Reusable
class EpisodeEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
) : ModelMapper<Episode, EpisodeEntity>() {

    override fun transformModelToEntity(input: Episode): EpisodeEntity {
        throw NoMappingAvailableException()
    }

    override fun transformEntityToModel(input: EpisodeEntity): Episode = Episode(
        id = input.id,
        name = input.name,
        date = input.date,
        number = input.number
    )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.MODEL_MAPPER_EPISODE, "error", error)
    }
}