package com.example.cryptocurrencyapp.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.domain.repository.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

open class SharedViewModel(
    private val connectivityObserver: ConnectivityObserver
) : ViewModel() {
    fun setupNetworkCallback(apiCallEvent: () -> Unit, setErrorStateEvent: (String) -> Unit) {
        connectivityObserver.observe().onEach { result ->
            when (result) {
                ConnectivityObserver.Status.AVAILABLE -> {
                    apiCallEvent()
                }
                else -> {
                    setErrorStateEvent("Check your internet connection. Pleas try again later.")
                }
            }
        }.launchIn(viewModelScope)
    }
}