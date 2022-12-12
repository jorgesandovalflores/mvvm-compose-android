package com.quebuu.mvvm_compose_android.common.domain.exception

import com.quebuu.mvvm_compose_android.R

class UnauthorizedException : BaseException(
    message = R.string.core_exception_unauthorized
)