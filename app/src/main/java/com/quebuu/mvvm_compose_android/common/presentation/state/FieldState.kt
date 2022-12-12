package com.quebuu.mvvm_compose_android.common.presentation.state

import androidx.annotation.StringRes

sealed class FieldState {
    object Valid : FieldState()
    class Invalid(@StringRes val messageResource: Int) : FieldState()
}
