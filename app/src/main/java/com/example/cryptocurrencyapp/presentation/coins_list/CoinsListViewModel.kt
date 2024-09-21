package com.example.cryptocurrencyapp.presentation.coins_list

import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.core.Resource
import com.example.cryptocurrencyapp.domain.repository.ConnectivityObserver
import com.example.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrencyapp.presentation.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    val getCoinsUseCase: GetCoinsUseCase,
    connectivityObserver: ConnectivityObserver
):SharedViewModel(connectivityObserver) {
    private val _uiState= MutableStateFlow(CoinsListUiState())
    val uiState= _uiState.asStateFlow()

    init {
        setupNetworkCallback(
            apiCallEvent = {getCoins()},
            setErrorStateEvent = {setErrorState(it)}
        )
    }

    fun getCoins(){
        if (uiState.value.coins.isNotEmpty()) return
        getCoinsUseCase().onEach { result->
            when(result){
                is Resource.Loading->{
                    setLoadingState()
                }
                is Resource.Success->{
                    _uiState.value=uiState.value.copy(coins = result.data?: emptyList(),isLoading = false, errorMessage = "")
                }
                is Resource.Error->{
                    setErrorState("Unknown error occurred. Please try again later.")
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setErrorState(message:String){
        if (uiState.value.coins.isNotEmpty()) return
        _uiState.value=uiState.value.copy(
            errorMessage = message,
            isLoading = false,
            coins = emptyList()
        )
    }

    private fun setLoadingState(){
        _uiState.value= uiState.value.copy(coins = emptyList(),isLoading = true, errorMessage = "")
    }
}