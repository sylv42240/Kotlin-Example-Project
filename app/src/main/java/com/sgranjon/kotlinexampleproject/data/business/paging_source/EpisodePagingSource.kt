package com.sgranjon.kotlinexampleproject.data.business.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sgranjon.kotlinexampleproject.data.entity.local.EpisodeEntity
import com.sgranjon.kotlinexampleproject.data.extensions.getPageNumberFromUrl
import com.sgranjon.kotlinexampleproject.data.extensions.getRefreshKey
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.mapper.remote.EpisodeRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject
import okio.IOException

@Reusable
class EpisodePagingSource @Inject constructor(
    private val apiManager: ApiManager,
    private val episodeRemoteEntityDataMapper: EpisodeRemoteEntityDataMapper
) : PagingSource<Int, EpisodeEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EpisodeEntity> {
        val nextPageNumber = params.key ?: 1
        return try {
            val response = apiManager.getAllEpisodes(nextPageNumber)
            val episodeRemoteList = response.results
            val episodeList =
                episodeRemoteEntityDataMapper.transformRemoteEntityList(episodeRemoteList)
            LoadResult.Page(
                data = episodeList,
                prevKey = getPageNumberFromUrl(response.info.prev),
                nextKey = getPageNumberFromUrl(response.info.next)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, EpisodeEntity>): Int? {
        return state.getRefreshKey()
    }

}