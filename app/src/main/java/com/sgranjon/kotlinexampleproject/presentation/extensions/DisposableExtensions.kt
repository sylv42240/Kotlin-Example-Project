package com.sgranjon.kotlinexampleproject.presentation.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Add Disposable to CompositeDisposable
 */
fun Disposable.addToComposite(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

/**
 * Allows Disposable to dispose safely
 */
fun Disposable?.disposeSafe() {
    this?.let {
        if (!isDisposed) {
            dispose()
        }
    }
}