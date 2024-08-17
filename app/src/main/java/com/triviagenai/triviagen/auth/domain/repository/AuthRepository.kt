package com.triviagenai.triviagen.auth.domain.repository

interface AuthRepository {
    suspend fun registerUser(email: String, password: String)
}