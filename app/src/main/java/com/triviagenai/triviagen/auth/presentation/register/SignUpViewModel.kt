package com.triviagenai.triviagen.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triviagenai.triviagen.auth.domain.usecase.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.exception.AuthRestException
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase
) : ViewModel() {
    suspend fun signUpUser(email: String, password: String, confirmPassword: String): String? {
        var errorMessage = validateData(email = email, password = password, confirmPassword = confirmPassword)

        if(errorMessage == null) {
            errorMessage = viewModelScope.async {
                try {
                    signUpUserUseCase.invoke(email, password)
                    null
                } catch (e: AuthRestException) {
                    "Something went wrong. Please try again later"
                }
            }.await()
        }

        return errorMessage
    }

    /**
     * Validates email and password provided by user.
     *
     * @return null if data is valid, otherwise returns error message
     */
    private fun validateData(email: String, password: String, confirmPassword: String): String? {
        if(email.isBlank()) {
            return "E-mail cannot be empty"
        }

        if(password.isBlank()) {
            return "Password cannot be empty"
        }

        if(!isEmailValid(email)) {
            return "Incorrect form of e-mail"
        }

        if(password.length < 8) {
            return "Password must have at least 8 characters"
        }

        if(password != confirmPassword) {
            return "Passwords do not match"
        }

        return null
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[^\\s@]+@[^\\s@]+\\.[a-zA-Z0-9]{2,}\$".toRegex()
        return email.matches(emailRegex)
    }
}
