package com.example.cryptocurrencyapp.presentation.coin_details

import com.example.cryptocurrencyapp.domain.model.CoinDetails

data class CoinDetailsUiState(
    val isLoading:Boolean=false,
    val coin: CoinDetails?=null,
    val coinId: String?=null,
    val errorMessage:String = ""
)
