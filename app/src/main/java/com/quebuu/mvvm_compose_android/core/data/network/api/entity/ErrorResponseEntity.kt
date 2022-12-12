package com.quebuu.mvvm_compose_android.core.data.network.api.entity

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody

data class ErrorResponseEntity(
    @field:SerializedName("code") val code: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("error_type") val errorType: String,
    @field:SerializedName("category") val category: String,
    @field:SerializedName("exceptionDetails") val exceptionDetails: List<ExceptionDetailDTO>
) {
    companion object {
        fun fromErrorBody(errorBody: ResponseBody) = try {
            Gson().fromJson(errorBody.charStream(), ErrorResponseEntity::class.java)
        } catch (e: Exception) {
            null
        }
    }
}

data class ExceptionDetailDTO(
    val component: String,
    val description: String,
    val endpoint: String
)
