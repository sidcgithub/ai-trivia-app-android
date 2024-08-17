package com.triviagenai.triviagen.auth.di

import com.triviagenai.triviagen.auth.data.repository.AuthRepositoryImpl
import com.triviagenai.triviagen.auth.domain.repository.AuthRepository
import com.triviagenai.triviagen.auth.domain.usecase.SignUpUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object SignUpModule {

    @Provides
    fun provideSignUpUserUseCase(authRepository: AuthRepository): SignUpUserUseCase {
        return SignUpUserUseCase(authRepository)
    }

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
    }
}