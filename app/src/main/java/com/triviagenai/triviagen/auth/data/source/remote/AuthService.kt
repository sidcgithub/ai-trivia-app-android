package com.triviagenai.triviagen.auth.data.source.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://oxgvdbfbepsdbsmzqeki.supabase.co",
        supabaseKey = ""
    ) {
        install(Auth)
    }
}