package com.quebuu.mvvm_compose_android.features.login.datasource.api

import com.quebuu.mvvm_compose_android.features.login.datasource.entity.FeatureLoginResponseEntity
import retrofit2.http.GET

interface FeatureLoginService {

    @GET("v1/user/1")
    suspend fun getUser(): FeatureLoginResponseEntity

}