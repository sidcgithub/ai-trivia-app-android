package com.triviagenai.triviagen.options.di

import android.app.Application
import com.triviagenai.triviagen.options.data.repository.UserPreferenceRepositoryImpl
import com.triviagenai.triviagen.options.data.source.local.UserSharedPreferences
import com.triviagenai.triviagen.options.domain.repository.UserPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OptionsModule {

    @Provides
    @Singleton
    fun provideUserSharedPreferences(application: Application): UserSharedPreferences {
        return UserSharedPreferences(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideUserPreferenceRepository(userSharedPreferences: UserSharedPreferences): UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(userSharedPreferences)
    }
}