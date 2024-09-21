package com.example.cryptocurrencyapp.di

import android.content.Context
import com.example.cryptocurrencyapp.data.repository.NetworkConnectivityObserver
import com.example.cryptocurrencyapp.domain.repository.ConnectivityObserver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideKtorClient(): HttpClient = HttpClient(Android) {
        install(JsonFeature) {
            serializer=KotlinxSerializer(Json {
                isLenient = true
                ignoreUnknownKeys = true
                expectSuccess = true
            }
            )
        }
    }

    @Provides
    @Singleton
    fun bindConnectivityObserver(
        @ApplicationContext context: Context
    ): ConnectivityObserver{
        return NetworkConnectivityObserver(context)
    }

}