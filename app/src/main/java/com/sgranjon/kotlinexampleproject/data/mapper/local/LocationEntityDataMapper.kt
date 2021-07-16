package com.sgranjon.kotlinexampleproject.data.mapper.local

import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceId
import com.sgranjon.kotlinexampleproject.data.entity.local.LocationEntity
import com.sgranjon.kotlinexampleproject.data.exception.NoMappingAvailableException
import com.sgranjon.kotlinexampleproject.data.mapper.base.ModelMapper
import com.sgranjon.kotlinexampleproject.data.model.Location
import dagger.Reusable
import javax.inject.Inject


@Reusable
class LocationEntityDataMapper @Inject constructor(
    private val traceComponent: TraceComponent,
) : ModelMapper<Location, LocationEntity>() {

    override fun transformModelToEntity(input: Location): LocationEntity {
        throw NoMappingAvailableException()
    }

    override fun transformEntityToModel(input: LocationEntity): Location = Location(
        id = input.id,
        name = input.name,
        type = input.type,
        dimension = input.dimension,
        residentList = input.residentList
    )

    override fun onMappingError(error: Exception) {
        traceComponent.traceError(TraceId.MODEL_MAPPER_LOCATION, "error", error)
    }
}