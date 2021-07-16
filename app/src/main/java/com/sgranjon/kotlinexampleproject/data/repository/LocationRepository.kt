package com.sgranjon.kotlinexampleproject.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.sgranjon.kotlinexampleproject.data.business.LocationBusinessHelper
import com.sgranjon.kotlinexampleproject.data.mapper.local.LocationEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.model.Location
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Reusable
class LocationRepository @Inject constructor(
    private val locationBusinessHelper: LocationBusinessHelper,
    private val locationEntityDataMapper: LocationEntityDataMapper
) {
    fun retrieveLocationList(): Flow<PagingData<Location>> =
        locationBusinessHelper.retrieveLocationList().map {
            it.map { location -> locationEntityDataMapper.transformEntityToModel(location) }
        }
}
