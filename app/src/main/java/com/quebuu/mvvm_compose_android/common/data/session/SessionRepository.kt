package com.quebuu.mvvm_compose_android.common.data.session

import android.accounts.Account
import com.quebuu.mvvm_compose_android.common.domain.usecase.UserModel

interface SessionRepository {
    suspend fun createUserModel(account: Account, userModel: UserModel)
    suspend fun getUserModel(): Account?
    suspend fun getUserModel(account: Account): UserModel?
    suspend fun updateUserModel(account: Account, userModel: UserModel)
    suspend fun clearUserModel()
}