package com.triviagenai.triviagen.auth.presentation.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.triviagenai.triviagen.R
import com.triviagenai.triviagen.auth.domain.usecase.SignUpUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.gotrue.exception.AuthRestException
import kotlinx.coroutines.async
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase
) : ViewModel() {
    suspend fun signUpUser(email: String, password: String, confirmPassword: String, context: Context): String? {
        var errorMessage = validateData(email = email, password = password, confirmPassword = confirmPassword, context = context)

        if(errorMessage == null) {
            errorMessage = viewModelScope.async {
                try {
                    signUpUserUseCase.invoke(email, password)
                    null
                } catch (e: AuthRestException) {
                    context.getString(R.string.something_went_wrong_please_try_again_later)
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
    private fun validateData(email: String, password: String, confirmPassword: String, context: Context): String? {
        if(email.isBlank()) {
            return context.getString(R.string.e_mail_cannot_be_empty)
        }

        if(password.isBlank()) {
            return context.getString(R.string.password_cannot_be_empty)
        }

        if(!isEmailValid(email)) {
            return context.getString(R.string.incorrect_form_of_e_mail)
        }

        if(password.length < 8) {
            return context.getString(R.string.password_must_have_at_least_8_characters)
        }

        if(password != confirmPassword) {
            return context.getString(R.string.passwords_do_not_match)
        }

        return null
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = "^[^\\s@]+@[^\\s@]+\\.[a-zA-Z0-9]{2,}\$".toRegex()
        return email.matches(emailRegex)
    }
}
