package com.sgranjon.kotlinexampleproject.data.business

import androidx.paging.PagingData
import com.sgranjon.kotlinexampleproject.data.business.pager.LocationPagingSource
import com.sgranjon.kotlinexampleproject.data.entity.local.LocationEntity
import com.sgranjon.kotlinexampleproject.data.extensions.createFlowFromPagingSource
import dagger.Reusable
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@Reusable
class LocationBusinessHelper @Inject constructor(
    private val locationPagingSource: LocationPagingSource
) {
    fun retrieveLocationList(): Flow<PagingData<LocationEntity>> {
        return createFlowFromPagingSource(locationPagingSource)
    }
}