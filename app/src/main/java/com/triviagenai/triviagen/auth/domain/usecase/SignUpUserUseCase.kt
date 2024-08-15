package com.triviagenai.triviagen.auth.domain.usecase

import com.triviagenai.triviagen.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.registerUser(email, password)
    }
}