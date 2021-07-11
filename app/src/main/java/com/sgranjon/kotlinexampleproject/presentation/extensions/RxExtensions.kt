package com.sgranjon.kotlinexampleproject.presentation.extensions

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

/**
 * Overloaded subscribe function that allows passing named parameters and subscribing on IO scheduler
 * @param onNext called for every emission in the Observable
 * @param onError called when Observable encounters an error
 * @param onComplete called when Observable finished
 */
fun <T : Any> Observable<T>.subscribeByIO(
    onNext: (T) -> Unit,
    onError: (Throwable) -> Unit,
    onComplete: () -> Unit
): Disposable =
    this.subscribeOn(Schedulers.io()).subscribeBy(
        onNext = onNext,
        onError = onError,
        onComplete = onComplete
    )

/**
 * Overloaded subscribe function that allows passing named parameters and subscribing on IO scheduler
 * @param onSuccess called when Single emits an item with success
 * @param onError called when Single encounters an error
 */
fun <T : Any> Single<T>.subscribeByIO(
    onSuccess: (T) -> Unit,
    onError: (Throwable) -> Unit
): Disposable =
    this.subscribeOn(Schedulers.io()).subscribeBy(onSuccess = onSuccess, onError = onError)

/**
 * Overloaded subscribe function that allows passing named parameters and subscribing on IO scheduler
 * @param onComplete called when Completable completes
 * @param onError called when Completable encounters an error
 */
fun Completable.subscribeByIO(
    onComplete: () -> Unit,
    onError: (Throwable) -> Unit
): Disposable =
    this.subscribeOn(Schedulers.io()).subscribeBy(onError = onError, onComplete = onComplete)