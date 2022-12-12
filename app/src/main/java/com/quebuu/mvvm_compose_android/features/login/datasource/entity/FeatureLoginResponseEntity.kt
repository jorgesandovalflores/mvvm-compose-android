package com.quebuu.mvvm_compose_android.features.login.datasource.entity

import com.google.gson.annotations.SerializedName
import com.quebuu.mvvm_compose_android.common.domain.usecase.UserModel

data class FeatureLoginResponseEntity(
    @field:SerializedName("email") val email: String? = "",
    @field:SerializedName("password") val password: String? = "",
    @field:SerializedName("firtName") val firstName: String? = "",
    @field:SerializedName("lastName") val lastName: String? = "",
    @field:SerializedName("id") val id: String? = "",
    @field:SerializedName("token") val token: String? = ""
)

// mapper
fun FeatureLoginResponseEntity.toModel() = UserModel(
    id = id ?: "",
    email = email ?: "",
    password = password ?: "",
    firstName = firstName ?: "",
    lastName = lastName ?: "",
    token = token ?: ""
)