package com.sgranjon.kotlinexampleproject.presentation.di.module

import com.sgranjon.kotlinexampleproject.presentation.component.dialog.DialogComponent
import com.sgranjon.kotlinexampleproject.presentation.component.dialog.DialogComponentImpl
import com.sgranjon.kotlinexampleproject.presentation.component.navigation.NavigationComponent
import com.sgranjon.kotlinexampleproject.presentation.component.navigation.NavigationComponentImpl
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponent
import com.sgranjon.kotlinexampleproject.presentation.component.snackbar.SnackbarComponentImpl
import com.sgranjon.kotlinexampleproject.presentation.di.annotation.PerApplication
import dagger.Module
import dagger.Provides
import dagger.Reusable


@Module
object ApplicationModule {

    @Provides
    @Reusable
    fun snackbarComponent(snackbarComponentImpl: SnackbarComponentImpl): SnackbarComponent =
        snackbarComponentImpl

    @Provides
    @Reusable
    fun dialogComponent(dialogComponent: DialogComponentImpl): DialogComponent = dialogComponent

    @Provides
    @PerApplication
    fun navigationComponent(navigationComponentImpl: NavigationComponentImpl): NavigationComponent =
        navigationComponentImpl
}