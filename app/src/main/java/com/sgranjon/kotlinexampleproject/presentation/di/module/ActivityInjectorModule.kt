package com.sgranjon.kotlinexampleproject.presentation.di.module

import com.sgranjon.kotlinexampleproject.presentation.di.annotation.PerActivity
import com.sgranjon.kotlinexampleproject.presentation.di.module.activity.MainActivityModule
import com.sgranjon.kotlinexampleproject.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityInjectorModule {

    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

}