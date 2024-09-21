package com.example.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tag(
    val id: String,
    val name: String,
    @SerialName("coin_counter")
    val coinCounter: Int?=null,
    @SerialName("ico_counter")
    val icoCounter: Int?=null
)