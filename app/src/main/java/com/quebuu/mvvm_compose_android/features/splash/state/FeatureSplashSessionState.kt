package com.quebuu.mvvm_compose_android.features.splash.state

sealed class FeatureSplashSessionState {
    object NotLoggedIn : FeatureSplashSessionState()
    object LoggedIn : FeatureSplashSessionState()
}
