package com.sgranjon.kotlinexampleproject.data.mapper.remote

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.local.LocationEntity
import com.sgranjon.kotlinexampleproject.data.entity.remote.LocationRemoteEntity
import com.sgranjon.kotlinexampleproject.data.exception.NoMappingAvailableException
import com.sgranjon.kotlinexampleproject.data.mapper.base.RemoteMapper
import dagger.Reusable
import javax.inject.Inject

@Reusable
class LocationRemoteEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent
) : RemoteMapper<LocationRemoteEntity, LocationEntity>() {

    override fun transformEntityToRemote(input: LocationEntity): LocationRemoteEntity {
        throw NoMappingAvailableException()
    }

    override fun transformRemoteToEntity(input: LocationRemoteEntity): LocationEntity =
        LocationEntity(
            id = input.id,
            name = input.name,
            type = input.type,
            dimension = input.dimension,
            residentList = input.residentList
        )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.REMOTE_MAPPER_LOCATION, "error", error)
    }
}
