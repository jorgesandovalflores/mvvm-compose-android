package com.quebuu.mvvm_compose_android.features.splash.module

import com.quebuu.mvvm_compose_android.common.data.session.SessionRepository
import com.quebuu.mvvm_compose_android.features.splash.usecase.FeatureSplashUseCase
import com.quebuu.mvvm_compose_android.features.splash.usecase.FeatureSplashUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureSplashModule {

    @Singleton
    @Provides
    fun providerFeatureSplashUseCase(sessionRepository: SessionRepository): FeatureSplashUseCase {
        return FeatureSplashUseCaseImpl(sessionRepository = sessionRepository)
    }

}