package com.sgranjon.kotlinexampleproject.presentation.di

import android.content.Context
import com.sgranjon.kotlinexampleproject.data.di.DataComponent
import com.sgranjon.kotlinexampleproject.presentation.MainApplication
import com.sgranjon.kotlinexampleproject.presentation.di.annotation.PerApplication
import com.sgranjon.kotlinexampleproject.presentation.di.module.ActivityInjectorModule
import com.sgranjon.kotlinexampleproject.presentation.di.module.ApplicationModule
import com.sgranjon.kotlinexampleproject.presentation.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityInjectorModule::class,
        ViewModelModule::class
    ], dependencies = [
        DataComponent::class
    ]
)
interface ApplicationComponent {

    fun inject(application: MainApplication)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dataComponent: DataComponent
        ): ApplicationComponent
    }
}