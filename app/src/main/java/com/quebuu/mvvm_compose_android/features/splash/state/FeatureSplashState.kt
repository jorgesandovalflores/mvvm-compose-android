package com.quebuu.mvvm_compose_android.features.splash.state

import com.quebuu.mvvm_compose_android.common.presentation.components.progress.LoadingState

sealed class FeatureSplashState {
    object Loading : FeatureSplashState(), LoadingState
    class Success(val destination: String) : FeatureSplashState()
}