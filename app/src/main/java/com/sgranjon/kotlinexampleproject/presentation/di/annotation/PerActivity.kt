package com.sgranjon.kotlinexampleproject.presentation.di.annotation

import javax.inject.Scope

/**
 * Scope annotation for Activity scope
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity