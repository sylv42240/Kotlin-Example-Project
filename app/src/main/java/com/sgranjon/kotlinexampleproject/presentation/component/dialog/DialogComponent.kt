package com.sgranjon.kotlinexampleproject.presentation.component.dialog

import android.content.Context
import androidx.annotation.StringRes
import com.sgranjon.kotlinexampleproject.R

interface DialogComponent {

    fun displayTextDialog(
        context: Context,
        @StringRes title: Int,
        @StringRes content: Int,
        @StringRes positiveText: Int = R.string.button_positive_text,
        @StringRes negativeText: Int = R.string.button_negative_text,
        isCancelable: Boolean = false,
        onPositiveClick: () -> Unit,
        onNegativeClick: () -> Unit = {},
        onCancel: () -> Unit = {}
    )

    fun displaySingleChoiceDialog(
        context: Context,
        @StringRes title: Int,
        @StringRes content: Int,
        @StringRes neutralText: Int = R.string.button_neutral_text,
        isCancelable: Boolean = false,
        onNeutralClick: () -> Unit = {},
        onCancel: () -> Unit = {}
    )

    fun displayErrorDialog(
        context: Context,
        @StringRes title: Int,
        throwable: Throwable,
        @StringRes neutralText: Int = R.string.button_neutral_text,
        isCancelable: Boolean = false,
        onNeutralClick: () -> Unit = {},
        onCancel: () -> Unit = {}
    )
}