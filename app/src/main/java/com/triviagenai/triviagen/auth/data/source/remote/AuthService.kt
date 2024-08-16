package com.triviagenai.triviagen.auth.data.source.remote

import com.triviagenai.triviagen.core.data.api.LocalPropertiesLoader
import com.triviagenai.triviagen.core.data.api.LocalPropertyType
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://oxgvdbfbepsdbsmzqeki.supabase.co",
        supabaseKey = LocalPropertiesLoader.getProperty(LocalPropertyType.SUPABASE_API_KEY)
    ) {
        install(Auth)
    }
}