package com.quebuu.mvvm_compose_android.common.data.extensions

import com.quebuu.mvvm_compose_android.common.domain.exception.CustomException
import com.quebuu.mvvm_compose_android.common.domain.exception.GenericException
import com.quebuu.mvvm_compose_android.common.domain.exception.UnauthorizedException
import com.quebuu.mvvm_compose_android.core.data.network.api.entity.ErrorResponseEntity
import retrofit2.HttpException

fun Exception.validateApiError(): Exception {
    return when (this) {
        is HttpException -> {
            this.response()?.let { response ->
                val error = response.errorBody()?.let { ErrorResponseEntity.fromErrorBody(it) }
                return when {
                    error == null -> GenericException()
                    (response.code() == 401 || response.code() == 403) -> UnauthorizedException()
                    else -> CustomException(
                        code = error.code,
                        description = error.description,
                        errorType = error.errorType,
                        category = error.category
                    )
                }
            }
            GenericException()
        }
        else -> GenericException()
    }
}