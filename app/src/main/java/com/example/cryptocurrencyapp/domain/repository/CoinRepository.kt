package com.example.cryptocurrencyapp.domain.repository

import com.example.cryptocurrencyapp.data.remote.dto.CoinDetailsDto
import com.example.cryptocurrencyapp.data.remote.dto.CoinDto
import com.example.cryptocurrencyapp.domain.model.Coin
import com.example.cryptocurrencyapp.domain.model.CoinDetails

interface CoinRepository {

    suspend fun getCoins():List<CoinDto>

    suspend fun getCoinDetailsById(coinId:String): CoinDetailsDto
}