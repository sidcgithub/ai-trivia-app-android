package com.triviagenai.triviagen.core.data.api

import com.triviagenai.triviagen.trivia.data.model.Round
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaGenApiService {
    @GET("trivia/")
    suspend fun getTriviaQuestions(@Query("topic") topic: String): Round
}