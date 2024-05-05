package com.triviagenai.triviagen.feature.trivia.di

import com.triviagenai.triviagen.feature.trivia.data.repository.TriviaRepositoryImpl
import com.triviagenai.triviagen.feature.trivia.domain.repository.TriviaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TriviaModule {

    @Binds
    abstract fun bindTriviaRepository(
        triviaRepositoryImpl: TriviaRepositoryImpl
    ): TriviaRepository
}