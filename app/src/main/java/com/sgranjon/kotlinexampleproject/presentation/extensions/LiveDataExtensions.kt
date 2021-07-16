package com.sgranjon.kotlinexampleproject.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Use this function to observe a call() with a block
 * @param owner LifecycleOwner, fragment or activity
 * @param observer Block
 */
fun <T> LiveData<T>.observeCall(owner: LifecycleOwner, observer: () -> Unit) {
    this.observe(owner, { observer() })
}

/**
 * Use this function to safely observe a postValue(T) with a block, executed only if value is not null
 * @param owner LifecycleOwner, fragment or activity
 * @param observer Block with the not null value
 */
fun <T> LiveData<T>.observeSafe(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner, { t ->
        t?.let { observer(it) }
    })
}
