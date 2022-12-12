package com.quebuu.mvvm_compose_android.features.login.datasource.repository

import com.quebuu.mvvm_compose_android.common.domain.usecase.UserModel
import com.quebuu.mvvm_compose_android.features.login.datasource.entity.FeatureLoginRequestEntity

interface FeatureLoginRepository {
    @Throws(Exception::class)
    suspend fun callLogin(request: FeatureLoginRequestEntity): UserModel
}