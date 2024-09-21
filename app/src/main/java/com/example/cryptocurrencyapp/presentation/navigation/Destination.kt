package com.example.cryptocurrencyapp.presentation.navigation

sealed class Destination(
    val route: String,
) {
    data object CoinsListDestination : Destination("coins_list_screen")
    data object CoinDetailDestination : Destination("coin_detail_screen")
}