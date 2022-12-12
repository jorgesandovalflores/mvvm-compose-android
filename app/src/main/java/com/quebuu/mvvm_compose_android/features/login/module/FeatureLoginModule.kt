package com.quebuu.mvvm_compose_android.features.login.module

import com.quebuu.mvvm_compose_android.BuildConfig
import com.quebuu.mvvm_compose_android.common.data.session.SessionRepository
import com.quebuu.mvvm_compose_android.core.data.network.api.ServiceBuilder
import com.quebuu.mvvm_compose_android.features.login.datasource.api.FeatureLoginService
import com.quebuu.mvvm_compose_android.features.login.datasource.repository.FeatureLoginRepository
import com.quebuu.mvvm_compose_android.features.login.datasource.repository.FeatureLoginRepositoryImpl
import com.quebuu.mvvm_compose_android.features.login.usecase.FeatureLoginUseCase
import com.quebuu.mvvm_compose_android.features.login.usecase.FeatureLoginUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FeatureLoginModule {

    @Singleton
    @Provides
    fun provideFeatureLoginService(): FeatureLoginService = ServiceBuilder.create(BuildConfig.API_BASE_URL)

    @Singleton
    @Provides
    fun providerFeatureLoginRepository(featureLoginService: FeatureLoginService): FeatureLoginRepository {
        return FeatureLoginRepositoryImpl(featureLoginService = featureLoginService)
    }

    @Singleton
    @Provides
    fun providerFeatureLoginUseCase(
        featureLoginRepository: FeatureLoginRepository,
        sessionRepository: SessionRepository
    ): FeatureLoginUseCase {
        return FeatureLoginUseCaseImpl(
            featureLoginRepository = featureLoginRepository,
            sessionRepository = sessionRepository
        )
    }

}