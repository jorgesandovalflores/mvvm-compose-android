package com.quebuu.mvvm_compose_android.features.splash.usecase

import com.quebuu.mvvm_compose_android.common.data.session.SessionRepository
import com.quebuu.mvvm_compose_android.features.splash.state.FeatureSplashSessionState
import javax.inject.Inject

class FeatureSplashUseCaseImpl
@Inject constructor(
    private val sessionRepository: SessionRepository
) : FeatureSplashUseCase {

    override suspend fun checkUserSession(): FeatureSplashSessionState {
        try {
            sessionRepository.getUserModel()
        } catch (e: Exception) {
            return FeatureSplashSessionState.NotLoggedIn
        }?.let { account ->
            return when (sessionRepository.getUserModel(account)) {
                null -> FeatureSplashSessionState.NotLoggedIn
                else -> {
                    FeatureSplashSessionState.LoggedIn
                }
            }
        } ?: return FeatureSplashSessionState.NotLoggedIn
    }

}