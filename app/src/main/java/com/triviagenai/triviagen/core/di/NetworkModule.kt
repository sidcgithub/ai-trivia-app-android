package com.triviagenai.triviagen.core.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.triviagenai.triviagen.core.data.api.TriviaGenApiService
import com.triviagenai.triviagen.trivia.data.model.Round
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val moshi = Moshi.Builder()
            .add(
                PolymorphicJsonAdapterFactory.of(Round::class.java, "type")
                    .withSubtype(
                        Round.TriviaRound::class.java,
                        "com.trivigenai.models.Round.TriviaRound"
                    )
                    .withSubtype(Round.Error::class.java, "com.trivigenai.models.Round.Error")
            )
            .add(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        return Retrofit.Builder()
            // If running backend locally use device IP
            .baseUrl("http://<your-ip-address>:8080")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideTriviaApiService(retrofit: Retrofit): TriviaGenApiService =
        retrofit.create(TriviaGenApiService::class.java)
}