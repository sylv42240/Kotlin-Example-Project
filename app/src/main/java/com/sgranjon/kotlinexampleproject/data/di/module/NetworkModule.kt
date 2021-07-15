package com.sgranjon.kotlinexampleproject.data.di.module

import com.google.gson.GsonBuilder
import com.sgranjon.kotlinexampleproject.BuildConfig
import com.sgranjon.kotlinexampleproject.data.manager.api.interceptor.NetworkInterceptor
import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object NetworkModule {

    /**
     * Retrofit is a heavy object, so we put it as a Singleton to prevent re-creation
     */
    @Provides
    @Singleton
    fun retrofitService(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory
    ): ApiRetrofitService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converter)
            .build()
            .create(ApiRetrofitService::class.java)

    /**
     * Retrofit Client
     */
    @Provides
    @Reusable
    fun provideApiOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(networkInterceptor)
        builder.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    /**
     * Retrofit Converter
     */
    @Provides
    @Reusable
    fun provideGsonConverter(): Converter.Factory =
        GsonConverterFactory.create(
            GsonBuilder().create()
        )

    /**
     * OkHttp Interceptor
     */
    @Provides
    @Reusable
    fun loggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
}