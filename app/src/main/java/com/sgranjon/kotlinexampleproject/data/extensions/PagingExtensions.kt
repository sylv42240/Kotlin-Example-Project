package com.sgranjon.kotlinexampleproject.data.extensions

import android.net.Uri

fun getPageNumberFromUrl(url: String?): Int? {
    return if (url != null) {
        val uri = Uri.parse(url)
        val pageNumberQuery = uri.getQueryParameter("page")
        pageNumberQuery?.toInt()
    } else {
        null
    }
}