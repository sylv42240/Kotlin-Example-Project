package com.sgranjon.kotlinexampleproject.presentation.component.error

import android.content.Context
import com.sgranjon.kotlinexampleproject.R
import com.sgranjon.kotlinexampleproject.data.exception.OfflineException
import com.sgranjon.kotlinexampleproject.data.exception.RequestFailException
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ErrorTranslater @Inject constructor() {

    /**
     * Translate Exception into String
     */
    fun translate(context: Context, throwable: Throwable): String {
        return context.getString(
            when (throwable) {
                is RequestFailException -> R.string.error_request_failed
                is OfflineException -> R.string.error_offline
                else -> R.string.error_general
            }
        )
    }
}