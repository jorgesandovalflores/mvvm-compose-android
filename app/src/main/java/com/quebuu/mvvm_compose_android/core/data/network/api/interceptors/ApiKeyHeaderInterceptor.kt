package com.quebuu.mvvm_compose_android.core.data.network.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyHeaderInterceptor(private val apiKeyName: String, private val apiKeyValue: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(with(chain.request().newBuilder()) {
            addHeader(apiKeyName, apiKeyValue)
            build()
        })
    }
}