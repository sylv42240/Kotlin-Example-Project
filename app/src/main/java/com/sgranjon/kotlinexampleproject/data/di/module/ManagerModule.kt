package com.sgranjon.kotlinexampleproject.data.di.module

import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManagerImpl
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DbManagerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

/**
 * Expose all managers here
 */
@Module
object ManagerModule {

    @Provides
    @Reusable
    fun dbManager(dbManagerImpl: DbManagerImpl): DbManager = dbManagerImpl

    @Provides
    @Reusable
    fun apiManager(apiManagerImpl: ApiManagerImpl): ApiManager = apiManagerImpl
}