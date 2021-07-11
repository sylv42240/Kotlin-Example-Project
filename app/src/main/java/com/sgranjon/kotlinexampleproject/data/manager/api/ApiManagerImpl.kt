package com.sgranjon.kotlinexampleproject.data.manager.api

import com.sgranjon.kotlinexampleproject.data.manager.api.service.ApiRetrofitService
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(private val apiRetrofitService: ApiRetrofitService) :
    ApiManager {

}