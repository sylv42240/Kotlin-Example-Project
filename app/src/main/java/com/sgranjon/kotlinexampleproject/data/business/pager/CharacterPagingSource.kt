package com.sgranjon.kotlinexampleproject.data.business.pager

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.sgranjon.kotlinexampleproject.data.entity.local.CharacterEntity
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.mapper.db.CharacterDBEntityDataMapper
import com.sgranjon.kotlinexampleproject.data.mapper.remote.CharacterRemoteEntityDataMapper
import dagger.Reusable
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class CharacterPagingSource @Inject constructor(
    private val apiManager: ApiManager,
    private val dbManager: DbManager,
    private val characterRemoteEntityDataMapper: CharacterRemoteEntityDataMapper,
    private val characterDBEntityDataMapper: CharacterDBEntityDataMapper
) :
    RxPagingSource<Int, CharacterEntity>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, CharacterEntity>> {
        val nextPageNumber = params.key ?: 1
        return apiManager.getAllCharacters(nextPageNumber).doOnSuccess {
            val characterEntity =
                characterRemoteEntityDataMapper.transformRemoteEntityList(it.results)
            dbManager.saveCharacterList(
                characterDBEntityDataMapper.transformEntityList(
                    characterEntity
                )
            )
        }.map {
            LoadResult.Page(
                data = characterRemoteEntityDataMapper.transformRemoteEntityList(it.results),
                prevKey = getPageNumberFromUrl(it.info.prev),
                nextKey = getPageNumberFromUrl(it.info.next)
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterEntity>): Int? {
        return null
    }

    private fun getPageNumberFromUrl(url: String?) = url?.split("=")?.last()?.toIntOrNull()

}