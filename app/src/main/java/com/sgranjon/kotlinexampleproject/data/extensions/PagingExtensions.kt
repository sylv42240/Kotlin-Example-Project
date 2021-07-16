package com.sgranjon.kotlinexampleproject.data.extensions

import android.net.Uri
import androidx.paging.PagingState

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