package com.quebuu.mvvm_compose_android.common.data.session

import android.accounts.Account
import android.accounts.AccountManager
import android.os.Bundle
import android.util.Log
import com.quebuu.mvvm_compose_android.common.domain.usecase.UserModel
import com.quebuu.mvvm_compose_android.core.data.network.account.QuebuuAccount
import javax.inject.Inject

class SessionRepositoryImpl
@Inject constructor(
    private val accountManager: AccountManager
) : SessionRepository {

    override suspend fun createUserModel(account: Account, userModel: UserModel) {
        val userData = Bundle().apply {
            putString(QuebuuAccount.ACCESS_TOKEN, userModel.token)
            putString(QuebuuAccount.USER_ID, userModel.id)
            putString(QuebuuAccount.USER_EMAIL, userModel.email)
            putString(QuebuuAccount.USER_FIRSTNAME, userModel.firstName)
            putString(QuebuuAccount.USER_LASTNAME, userModel.lastName)
        }
        accountManager.addAccountExplicitly(account, null, userData)
    }

    override suspend fun getUserModel(): Account? {
        return accountManager.accounts.firstOrNull {
            it.type == QuebuuAccount.ACCOUNT_TYPE
        }
    }

    override suspend fun getUserModel(account: Account): UserModel? {
        return try {
            UserModel(
                token = accountManager.getUserData(account, QuebuuAccount.ACCESS_TOKEN),
                id = accountManager.getUserData(account, QuebuuAccount.USER_ID),
                email = accountManager.getUserData(account, QuebuuAccount.USER_EMAIL),
                firstName = accountManager.getUserData(account, QuebuuAccount.USER_FIRSTNAME),
                lastName = accountManager.getUserData(account, QuebuuAccount.USER_LASTNAME),
                password = ""
            )
        } catch (e: Exception) {
            Log.w(SessionRepository::class.simpleName, "Error retrieving LoginData", e)
            null
        }
    }

    override suspend fun updateUserModel(account: Account, userModel: UserModel) {
        accountManager.setUserData(account, QuebuuAccount.ACCESS_TOKEN, userModel.token)
        accountManager.setUserData(account, QuebuuAccount.USER_ID, userModel.id)
        accountManager.setUserData(account, QuebuuAccount.USER_EMAIL, userModel.email)
        accountManager.setUserData(account, QuebuuAccount.USER_FIRSTNAME, userModel.firstName)
        accountManager.setUserData(account, QuebuuAccount.USER_LASTNAME, userModel.lastName)
    }

    override suspend fun clearUserModel() {
        accountManager.accounts.firstOrNull {
            it.type == QuebuuAccount.ACCOUNT_TYPE
        }?.let(accountManager::removeAccountExplicitly)
    }

}