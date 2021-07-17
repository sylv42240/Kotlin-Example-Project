package com.sgranjon.kotlinexampleproject.data

import android.content.Context
import com.sgranjon.kotlinexampleproject.data.di.module.DataModule
import com.sgranjon.kotlinexampleproject.data.di.module.ManagerModule
import com.sgranjon.kotlinexampleproject.data.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ManagerModule::class, NetworkModule::class])
internal interface TestComponent {

    fun inject(apiManagerTests: ApiManagerTests)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): TestComponent
    }
}