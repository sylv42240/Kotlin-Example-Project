package com.sgranjon.kotlinexampleproject.data.extensions

import android.net.Uri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.Flow

const val API_PAGE_SIZE = 20
const val PREFETCH_DISTANCE = 2

fun getPageNumberFromUrl(url: String?): Int? {
    return if (url != null) {
        val uri = Uri.parse(url)
        val pageNumberQuery = uri.getQueryParameter("page")
        pageNumberQuery?.toInt()
    } else {
        null
    }
}

fun PagingState<Int, out Any>.getRefreshKey(): Int? {
    return this.anchorPosition?.let { anchorPosition ->
        this.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
            ?: this.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
    }
}

fun <T : Any> createFlowFromPagingSource(pagingSource: PagingSource<Int, T>): Flow<PagingData<T>> {
    return Pager(config = PagingConfig(
        pageSize = API_PAGE_SIZE,
        prefetchDistance = PREFETCH_DISTANCE
    ),
        pagingSourceFactory = { pagingSource }
    ).flow
}
