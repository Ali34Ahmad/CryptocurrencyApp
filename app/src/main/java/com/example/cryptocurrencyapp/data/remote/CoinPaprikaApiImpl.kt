package com.example.cryptocurrencyapp.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.cryptocurrencyapp.core.Constants
import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import javax.inject.Inject

class CoinPaprikaApiImpl @Inject constructor(
    private val client:HttpClient
): CoinPaprikaApi {
    override suspend fun getCoins(): List<CoinDto> {
        return client.get {
            url(Constants.COINS)
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return client.get(
            "${Constants.COINS}/$coinId"
            )
    }
}

