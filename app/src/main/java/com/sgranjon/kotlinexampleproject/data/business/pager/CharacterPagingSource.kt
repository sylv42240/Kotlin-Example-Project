package com.sgranjon.kotlinexampleproject.data.business.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.extensions.getPageNumberFromUrl
import com.sgranjon.kotlinexampleproject.data.extensions.getRefreshKey
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.mapper.db.CharacterDBEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.mapper.remote.CharacterRemoteEntityDataMapper
import dagger.Reusable
import javax.inject.Inject
import okio.IOException

@Reusable
class CharacterPagingSource @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DbManager,
    private val characterRemoteEntityDataMapper: CharacterRemoteEntityDataMapper,
    private val characterDBEntityDataMapper: CharacterDBEntityDataMapper
) : PagingSource<Int, CharacterEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterEntity> {
        val nextPageNumber = params.key ?: 1
        return try {
            val response = apiManager.getAllCharacters(nextPageNumber)
            val characterRemoteList = response.results
            val characterList =
                characterRemoteEntityDataMapper.transformRemoteEntityList(characterRemoteList)
            dbManager.saveCharacterList(
                characterDBEntityDataMapper.transformEntityList(
                    characterList
                )
            )
            LoadResult.Page(
                data = characterList,
                prevKey = getPageNumberFromUrl(response.info.prev),
                nextKey = getPageNumberFromUrl(response.info.next)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return state.getRefreshKey()
    }

}