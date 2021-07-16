package com.sgranjon.kotlinexampleproject.data.business.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sgranjon.kotlinexampleproject.data.entity.local.LocationEntity
import com.sgranjon.kotlinexampleproject.data.extensions.getPageNumberFromUrl
import com.sgranjon.kotlinexampleproject.data.extensions.getRefreshKey
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.mapper.remote.LocationRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject
import okio.IOException

@Reusable
class LocationPagingSource @Inject constructor(
    private val apiManager: ApiManager,
    private val locationRemoteEntityDataMapper: LocationRemoteEntityDataMapper
) : PagingSource<Int, LocationEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LocationEntity> {
        val nextPageNumber = params.key ?: 1
        return try {
            val response = apiManager.getAllLocations(nextPageNumber)
            val locationRemoteList = response.results
            val locationList =
                locationRemoteEntityDataMapper.transformRemoteEntityList(locationRemoteList)
            LoadResult.Page(
                data = locationList,
                prevKey = getPageNumberFromUrl(response.info.prev),
                nextKey = getPageNumberFromUrl(response.info.next)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LocationEntity>): Int? {
        return state.getRefreshKey()
    }

}