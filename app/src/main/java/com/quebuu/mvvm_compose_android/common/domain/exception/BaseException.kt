package com.quebuu.mvvm_compose_android.common.domain.exception

import androidx.annotation.StringRes
import com.quebuu.mvvm_compose_android.R

open class BaseException(
    @StringRes message: Int = R.string.core_exception_generic
) : Exception()