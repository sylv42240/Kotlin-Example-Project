package com.sgranjon.kotlinexampleproject.presentation.component.snackbar

import android.content.Context
import android.view.View
import androidx.annotation.StringRes

interface SnackbarComponent {
    fun displaySuccess(context: Context, @StringRes content: Int, view: View?)
    fun displayError(context: Context, throwable: Throwable, view: View?)
    fun displayWarning(context: Context, @StringRes content: Int, view: View?)
}