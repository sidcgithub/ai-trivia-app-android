@file:Suppress("unused", "unused")

package com.triviagenai.triviagen.trivia.di

import com.triviagenai.triviagen.trivia.data.repository.TriviaRepositoryImpl
import com.triviagenai.triviagen.trivia.domain.repository.TriviaRepository
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