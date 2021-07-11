package com.sgranjon.kotlinexampleproject.presentation.component.dialog

import android.app.Dialog
import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sgranjon.kotlinexampleproject.presentation.component.error.ErrorTranslater
import javax.inject.Inject

class DialogComponentImpl @Inject constructor(private val errorTranslater: ErrorTranslater) :
    DialogComponent {

    private var currentDialog: Dialog? = null

    override fun displayTextDialog(
        context: Context,
        title: Int,
        content: Int,
        positiveText: Int,
        negativeText: Int,
        isCancelable: Boolean,
        onPositiveClick: () -> Unit,
        onNegativeClick: () -> Unit,
        onCancel: () -> Unit
    ) {
        currentDialog = MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(title))
            .setMessage(context.getString(content))
            .setNegativeButton(context.getString(negativeText)) { dialog, _ ->
                onNegativeClick()
                dialog.dismiss()
            }
            .setPositiveButton(context.getString(positiveText)) { dialog, _ ->
                onPositiveClick()
                dialog.dismiss()
            }
            .setOnCancelListener {
                onCancel()
            }
            .setCancelable(isCancelable)
            .show()
    }

    override fun displaySingleChoiceDialog(
        context: Context,
        title: Int,
        content: Int,
        neutralText: Int,
        isCancelable: Boolean,
        onNeutralClick: () -> Unit,
        onCancel: () -> Unit
    ) {
        currentDialog = MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(title))
            .setMessage(context.getString(content))
            .setNeutralButton(context.getString(neutralText)) { dialog, _ ->
                onNeutralClick()
                dialog.dismiss()
            }
            .setOnCancelListener {
                onCancel()
            }
            .setCancelable(isCancelable)
            .show()
    }

    override fun displayErrorDialog(
        context: Context,
        title: Int,
        throwable: Throwable,
        neutralText: Int,
        isCancelable: Boolean,
        onNeutralClick: () -> Unit,
        onCancel: () -> Unit
    ) {
        currentDialog = MaterialAlertDialogBuilder(context)
            .setTitle(context.getString(title))
            .setMessage(errorTranslater.translate(context, throwable))
            .setNeutralButton(context.getString(neutralText)) { dialog, _ ->
                onNeutralClick()
                dialog.dismiss()
            }
            .setOnCancelListener {
                onCancel()
            }
            .setCancelable(isCancelable)
            .show()
    }
}