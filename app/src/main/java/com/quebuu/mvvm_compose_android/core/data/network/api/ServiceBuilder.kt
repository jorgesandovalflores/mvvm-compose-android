package com.quebuu.mvvm_compose_android.core.data.network.api

import com.google.gson.Gson
import com.quebuu.mvvm_compose_android.core.data.network.api.interceptors.ApiKeyHeaderInterceptor
import com.quebuu.mvvm_compose_android.core.data.network.api.interceptors.NetworkInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    inline fun <reified T> create(
        baseUrl: String,
        apiKey: Pair<String, String>? = null,
        gson: Gson? = null
    ): T {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)

        apiKey?.let {
            clientBuilder.addInterceptor(ApiKeyHeaderInterceptor(it.first, it.second))
        }

        clientBuilder.addInterceptor(NetworkInterceptor.httpLogging())

        val client = clientBuilder.build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(if (gson != null) GsonConverterFactory.create(gson) else GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}