package com.quebuu.mvvm_compose_android.common.data

import android.accounts.AccountManager
import android.content.Context
import com.quebuu.mvvm_compose_android.common.data.session.SessionRepository
import com.quebuu.mvvm_compose_android.common.data.session.SessionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Singleton
    @Provides
    fun providerSessionRepository(@ApplicationContext base: Context): SessionRepository {
        return SessionRepositoryImpl(accountManager = AccountManager.get(base))
    }

}