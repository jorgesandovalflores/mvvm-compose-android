package com.quebuu.mvvm_compose_android.features.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quebuu.mvvm_compose_android.common.presentation.components.progress.mapIsLoadingState
import com.quebuu.mvvm_compose_android.common.presentation.extensions.runCatchingWithMinimumTime
import com.quebuu.mvvm_compose_android.core.presentation.di.IoDispatcher
import com.quebuu.mvvm_compose_android.features.home.navigation.FeatureHomeNavigation
import com.quebuu.mvvm_compose_android.features.splash.state.FeatureSplashSessionState
import com.quebuu.mvvm_compose_android.features.splash.state.FeatureSplashState
import com.quebuu.mvvm_compose_android.features.splash.usecase.FeatureSplashUseCase
import com.quebuu.mvvm_compose_android.features.welcome.navigation.FeatureWelcomeNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureSplashViewModel
@Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val featureSplashUseCase: FeatureSplashUseCase
) : ViewModel() {

    private val _destinationFlow = MutableStateFlow<FeatureSplashState?>(null)
    val destinationFlow: Flow<FeatureSplashState?> = _destinationFlow

    val loadingFlow = destinationFlow.mapIsLoadingState()

    fun checkSession() {
        viewModelScope.launch(ioDispatcher) {
            runCatchingWithMinimumTime(1000) {
                featureSplashUseCase.checkUserSession()
            }.onSuccess { userSessionState ->
                when (userSessionState) {
                    FeatureSplashSessionState.LoggedIn -> _destinationFlow.emit(FeatureSplashState.Success(FeatureHomeNavigation.destination))
                    FeatureSplashSessionState.NotLoggedIn -> _destinationFlow.emit(FeatureSplashState.Success(FeatureWelcomeNavigation.destination))
                }
            }
        }
    }

}