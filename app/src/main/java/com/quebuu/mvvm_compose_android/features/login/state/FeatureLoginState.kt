package com.quebuu.mvvm_compose_android.features.login.state

import androidx.annotation.StringRes
import com.quebuu.mvvm_compose_android.common.presentation.components.progress.LoadingState

sealed class FeatureLoginState {
    object Loading : FeatureLoginState(), LoadingState
    object Success : FeatureLoginState()
    class Error(@StringRes val messageResource: Int) : FeatureLoginState()
}