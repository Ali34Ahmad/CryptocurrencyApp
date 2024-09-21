package com.example.cryptocurrencyapp.presentation.coins_list

import com.example.cryptocurrencyapp.domain.model.Coin

data class CoinsListUiState(
    val isLoading:Boolean=false,
    val coins:List<Coin> = emptyList(),
    val errorMessage:String = ""
)
