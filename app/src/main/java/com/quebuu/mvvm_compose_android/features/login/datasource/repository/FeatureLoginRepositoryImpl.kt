package com.quebuu.mvvm_compose_android.features.login.datasource.repository

import com.quebuu.mvvm_compose_android.common.data.extensions.validateApiError
import com.quebuu.mvvm_compose_android.common.domain.usecase.UserModel
import com.quebuu.mvvm_compose_android.features.login.datasource.entity.FeatureLoginRequestEntity

import com.quebuu.mvvm_compose_android.features.login.datasource.api.FeatureLoginService
import com.quebuu.mvvm_compose_android.features.login.datasource.entity.toModel
import javax.inject.Inject

class FeatureLoginRepositoryImpl
@Inject constructor(
    private val featureLoginService: FeatureLoginService
) : FeatureLoginRepository {

    @Throws(Exception::class)
    override suspend fun callLogin(request: FeatureLoginRequestEntity): UserModel {
        return try {
            featureLoginService.getUser().toModel()
        } catch (exception: Exception) {
            throw exception.validateApiError()
        }
    }

}