package com.sgranjon.kotlinexampleproject.data.di

import android.content.Context
import com.sgranjon.kotlinexampleproject.data.di.module.DataModule
import com.sgranjon.kotlinexampleproject.data.di.module.ManagerModule
import com.sgranjon.kotlinexampleproject.data.di.module.NetworkModule
import com.sgranjon.kotlinexampleproject.data.repository.CharacterRepository
import com.sgranjon.kotlinexampleproject.data.repository.EpisodeRepository
import com.sgranjon.kotlinexampleproject.data.repository.LocationRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, ManagerModule::class, NetworkModule::class])
interface DataComponent {

    /**
     * Expose all repositories here
     */

    fun characterRepository(): CharacterRepository
    fun episodeRepository(): EpisodeRepository
    fun locationRepository(): LocationRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }

}