package com.quebuu.mvvm_compose_android.features.splash.usecase

import com.quebuu.mvvm_compose_android.features.splash.state.FeatureSplashSessionState

interface FeatureSplashUseCase {
    suspend fun checkUserSession(): FeatureSplashSessionState
}