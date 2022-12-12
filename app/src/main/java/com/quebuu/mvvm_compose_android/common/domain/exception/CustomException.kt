package com.quebuu.mvvm_compose_android.common.domain.exception

class CustomException(
    code: String,
    description: String,
    errorType: String,
    category: String
) : BaseException()