package com.sgranjon.kotlinexampleproject.data.di.module

import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManager
import com.sgranjon.kotlinexampleproject.data.manager.api.ApiManagerImpl
import com.sgranjon.kotlinexampleproject.data.manager.db.DBManager
import com.sgranjon.kotlinexampleproject.data.manager.db.DBManagerImpl
import com.sgranjon.kotlinexampleproject.data.manager.preferences.SharedPrefManager
import com.sgranjon.kotlinexampleproject.data.manager.preferences.SharedPrefManagerImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object ManagerModule {

    @Provides
    @Reusable
    fun dbManager(dbManagerImpl: DBManagerImpl): DBManager = dbManagerImpl

    @Provides
    @Reusable
    fun apiManager(apiManagerImpl: ApiManagerImpl): ApiManager = apiManagerImpl

    @Provides
    @Reusable
    fun sharedPrefManager(sharedPrefManagerImpl: SharedPrefManagerImpl): SharedPrefManager =
        sharedPrefManagerImpl
}