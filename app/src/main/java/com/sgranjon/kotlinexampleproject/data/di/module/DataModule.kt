package com.sgranjon.kotlinexampleproject.data.di.module

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sgranjon.kotlinexampleproject.BuildConfig
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponent
import com.sgranjon.kotlinexampleproject.data.component.trace.TraceComponentImpl
import com.sgranjon.kotlinexampleproject.data.manager.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    @Reusable
    fun gson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase = AppDatabase.getDatabase(context)

    /**
     * Components
     */

    @Provides
    @Reusable
    fun traceComponent(): TraceComponent = TraceComponentImpl(BuildConfig.DEBUG)
}


