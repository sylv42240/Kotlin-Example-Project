package com.sgranjon.kotlinexampleproject.presentation.di.annotation

import javax.inject.Scope

/**
 * Scope annotation for Application scope
 * Similar to Singleton scope - Need to separate scopes because DataComponent is Singleton
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerApplication