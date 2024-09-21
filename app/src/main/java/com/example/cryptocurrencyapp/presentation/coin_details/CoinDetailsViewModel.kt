package com.example.cryptocurrencyapp.presentation.coin_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.core.Constants
import com.example.cryptocurrencyapp.core.Resource
import com.example.cryptocurrencyapp.domain.model.CoinDetails
import com.example.cryptocurrencyapp.domain.repository.ConnectivityObserver
import com.example.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import com.example.cryptocurrencyapp.presentation.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle,
    connectivityObserver: ConnectivityObserver
) : SharedViewModel(connectivityObserver) {
    private val _uiState = MutableStateFlow(CoinDetailsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            _uiState.value = uiState.value.copy(coinId = coinId)
            setupNetworkCallback(
                apiCallEvent = {
                    getCoinById(uiState.value.coinId)
                },
                setErrorStateEvent = { setErrorState(it) }
            )
        }
    }

    private var getCoinJob: Job? = null
    fun getCoinById(coinId: String?) {
        if (coinId == null || uiState.value.coin != null) return
        getCoinJob?.cancel()
        getCoinJob = getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    setLoadingState()
                }

                is Resource.Success -> {
                    _uiState.value =
                        uiState.value.copy(coin = result.data, isLoading = false, errorMessage = "")
                }

                is Resource.Error -> {
                    setErrorState("Unknown error occurred. Please try again later.")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setErrorState(message: String) {
        if (uiState.value.coin != null) return
        _uiState.value = uiState.value.copy(
            errorMessage = message,
            isLoading = false,
        )
    }

    private fun setLoadingState() {
        _uiState.value = uiState.value.copy(isLoading = true, errorMessage = "")
    }
}