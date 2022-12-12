package com.quebuu.mvvm_compose_android.core.data.network.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.NetworkErrorException
import android.content.Context
import android.os.Bundle

class QuebuuAccount(base: Context) : AbstractAccountAuthenticator(base) {
    companion object {
        const val ACCOUNT_TYPE = "com.quebuu.project_model.auth"
        const val ACCESS_TOKEN = "com.quebuu.project_model.auth.ACCESS_TOKEN"
        const val USER_ID = "com.quebuu.project_model.auth.USER_ID"
        const val USER_FIRSTNAME = "com.quebuu.project_model.auth.USER_FIRSTNAME"
        const val USER_LASTNAME = "com.quebuu.project_model.auth.USER_LASTNAME"
        const val USER_EMAIL = "com.quebuu.project_model.auth.USER_EMAIL"
    }

    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle = throw UnsupportedOperationException()

    @Throws(NetworkErrorException::class)
    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle? = null

    @Throws(NetworkErrorException::class)
    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle? = null

    @Throws(NetworkErrorException::class)
    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle = throw UnsupportedOperationException()

    override fun getAuthTokenLabel(
        authTokenType: String?
    ): String = throw UnsupportedOperationException()

    @Throws(NetworkErrorException::class)
    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle = throw UnsupportedOperationException()

    @Throws(NetworkErrorException::class)
    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle = throw UnsupportedOperationException()
}