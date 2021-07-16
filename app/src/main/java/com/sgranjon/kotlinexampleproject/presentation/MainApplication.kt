package com.sgranjon.kotlinexampleproject.presentation

import android.app.Application
import com.sgranjon.kotlinexampleproject.data.di.DaggerDataComponent
import com.sgranjon.kotlinexampleproject.presentation.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.factory()
            .create(this, DaggerDataComponent.factory().create(this))
            .inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}