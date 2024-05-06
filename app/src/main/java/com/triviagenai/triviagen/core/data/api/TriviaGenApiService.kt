package com.triviagenai.triviagen.core.data.api

import com.triviagenai.triviagen.trivia.domain.model.TriviaQuestion
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaGenApiService {
    @GET("trivia/questions")
    suspend fun getTriviaQuestions(@Query("category") category: String): List<TriviaQuestion>
}