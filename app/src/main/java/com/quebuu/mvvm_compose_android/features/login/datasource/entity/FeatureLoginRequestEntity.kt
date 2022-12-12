package com.quebuu.mvvm_compose_android.features.login.datasource.entity

import com.google.gson.annotations.SerializedName

data class FeatureLoginRequestEntity(
    @field:SerializedName("username") val username: String,
    @field:SerializedName("password") val password: String
)