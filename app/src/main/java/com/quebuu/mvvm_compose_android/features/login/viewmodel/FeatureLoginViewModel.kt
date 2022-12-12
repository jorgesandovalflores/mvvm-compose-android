package com.quebuu.mvvm_compose_android.features.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quebuu.mvvm_compose_android.R
import com.quebuu.mvvm_compose_android.common.presentation.components.progress.mapIsLoadingState
import com.quebuu.mvvm_compose_android.common.presentation.extensions.runCatchingWithMinimumTime
import com.quebuu.mvvm_compose_android.common.presentation.state.DataState
import com.quebuu.mvvm_compose_android.common.presentation.state.FieldState
import com.quebuu.mvvm_compose_android.core.presentation.di.IoDispatcher
import com.quebuu.mvvm_compose_android.features.login.state.FeatureLoginState
import com.quebuu.mvvm_compose_android.features.login.usecase.FeatureLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeatureLoginViewModel
@Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val featureLoginUseCase: FeatureLoginUseCase
) : ViewModel() {

    private val _destinationFlow = MutableStateFlow<FeatureLoginState?>(null)
    val destinationFlow: Flow<FeatureLoginState?> = _destinationFlow

    private val _emailState = MutableStateFlow<FieldState>(FieldState.Valid)
    val emailState: Flow<FieldState> = _emailState

    private val _passwordState = MutableStateFlow<FieldState>(FieldState.Valid)
    val passwordState: Flow<FieldState> = _passwordState

    val loadingFlow = destinationFlow.mapIsLoadingState()

    fun login(email: String, password: String) {
        viewModelScope.launch(ioDispatcher) {
            val validateEmail = async { validateEmail(email) }
            val validatePassword = async { validatePassword(password) }

            if (!validateEmail.await() || !validatePassword.await()) return@launch

            _destinationFlow.emit(FeatureLoginState.Loading)

            runCatchingWithMinimumTime {
                featureLoginUseCase.callLogin(email, password)
            }.onSuccess {
                _destinationFlow.emit(FeatureLoginState.Success)
            }.onFailure {
                _destinationFlow.emit(FeatureLoginState.Error(R.string.core_exception_generic))
            }
        }
    }

    private suspend fun validateEmail(email: String): Boolean {
        val state = featureLoginUseCase.validateEmail(email)

        when (state) {
            DataState.Empty -> FieldState.Invalid(R.string.feature_login_empty_email_error)
            DataState.Invalid -> FieldState.Invalid(R.string.feature_login_email_error)
            else -> FieldState.Valid
        }.let {
            _emailState.emit(it)
        }

        return state == DataState.Valid
    }

    private suspend fun validatePassword(password: String): Boolean {
        val state = featureLoginUseCase.validatePassword(password)

        when (state) {
            DataState.Empty -> FieldState.Invalid(R.string.feature_login_empty_password_error)
            DataState.Invalid -> FieldState.Invalid(R.string.feature_login_password_error)
            else -> FieldState.Valid
        }.let {
            _passwordState.emit(it)
        }

        return state == DataState.Valid
    }

    private suspend fun clearState() {
        viewModelScope.launch {
            _destinationFlow.emit(null)
        }
    }

}