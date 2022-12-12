package com.quebuu.mvvm_compose_android.features.login.usecase

import android.accounts.Account
import com.quebuu.mvvm_compose_android.common.data.session.SessionRepository
import com.quebuu.mvvm_compose_android.common.presentation.state.DataState
import com.quebuu.mvvm_compose_android.common.domain.util.Constants
import com.quebuu.mvvm_compose_android.core.data.network.account.QuebuuAccount
import com.quebuu.mvvm_compose_android.features.login.datasource.entity.FeatureLoginRequestEntity
import com.quebuu.mvvm_compose_android.features.login.datasource.repository.FeatureLoginRepository
import javax.inject.Inject

class FeatureLoginUseCaseImpl
@Inject constructor(
    private val featureLoginRepository: FeatureLoginRepository,
    private val sessionRepository: SessionRepository
) : FeatureLoginUseCase {

    override suspend fun callLogin(username: String, password: String) {
        val loginData = featureLoginRepository.callLogin(FeatureLoginRequestEntity(
            username = username,
            password = password
        ))

        sessionRepository.createUserModel(
            Account(username, QuebuuAccount.ACCOUNT_TYPE),
            loginData
        )
    }

    override suspend fun validateEmail(email: String): DataState {
        return when {
            email.isEmpty() -> DataState.Empty
            !email.matches(Regex(Constants.Regex.EMAIL)) -> DataState.Invalid
            else -> DataState.Valid
        }
    }

    override suspend fun validatePassword(password: String): DataState {
        return when {
            password.isEmpty() -> DataState.Empty
            password.length < PASSWORD_MINIMUM_LENGTH || password.length > PASSWORD_MAXIMUM_LENGTH -> DataState.Invalid
            else -> DataState.Valid
        }
    }

    companion object {
        private const val PASSWORD_MINIMUM_LENGTH = 8
        private const val PASSWORD_MAXIMUM_LENGTH = 20
    }

}