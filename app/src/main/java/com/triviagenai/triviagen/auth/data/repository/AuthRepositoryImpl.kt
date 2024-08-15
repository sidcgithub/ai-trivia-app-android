package com.triviagenai.triviagen.auth.data.repository

import com.triviagenai.triviagen.auth.data.source.remote.SupabaseClient
import com.triviagenai.triviagen.auth.domain.repository.AuthRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthRepositoryImpl: AuthRepository {
    val supabase = SupabaseClient.supabase

    override suspend fun registerUser(email: String, password: String) {
        supabase.auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }
}