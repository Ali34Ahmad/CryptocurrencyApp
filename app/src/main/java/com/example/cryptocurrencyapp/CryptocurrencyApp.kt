package com.example.cryptocurrencyapp

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrencyapp.core.Constants
import com.example.cryptocurrencyapp.presentation.coin_details.CoinDetailsScreen
import com.example.cryptocurrencyapp.presentation.coin_details.CoinDetailsUiState
import com.example.cryptocurrencyapp.presentation.coin_details.CoinDetailsViewModel
import com.example.cryptocurrencyapp.presentation.coins_list.CoinsListScreen
import com.example.cryptocurrencyapp.presentation.coins_list.CoinsListUiState
import com.example.cryptocurrencyapp.presentation.coins_list.CoinsListViewModel
import com.example.cryptocurrencyapp.presentation.navigation.Destination

@Composable
fun CryptocurrencyApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destination.CoinsListDestination.route
    ) {
        composable(Destination.CoinsListDestination.route) {
            val coinsListViewModel: CoinsListViewModel = hiltViewModel()
            val coinsListUiState = coinsListViewModel.uiState.collectAsStateWithLifecycle()
            CoinsListScreen(
                uiState = coinsListUiState.value,
                onGetCoinsEvent = coinsListViewModel::getCoins,
                navController = navController,
            )
        }
        composable(Destination.CoinDetailDestination.route + "/{${Constants.PARAM_COIN_ID}}") {
            val coinDetailsViewModel: CoinDetailsViewModel = hiltViewModel()
            val coinDetailsUiState = coinDetailsViewModel.uiState.collectAsStateWithLifecycle()
            CoinDetailsScreen(
                uiState = coinDetailsUiState.value,
                onGetCoinByIdEvent = coinDetailsViewModel::getCoinById
            )

        }
    }
}