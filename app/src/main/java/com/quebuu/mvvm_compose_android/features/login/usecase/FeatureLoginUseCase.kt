package com.quebuu.mvvm_compose_android.features.login.usecase

import com.quebuu.mvvm_compose_android.common.presentation.state.DataState

interface FeatureLoginUseCase {
    @Throws(Exception::class)
    suspend fun callLogin(username: String, password: String)
    suspend fun validateEmail(email: String): DataState
    suspend fun validatePassword(password: String): DataState
}