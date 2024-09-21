package com.example.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val subscribers: Int?=null,
    val contributors: Int?=null,
    val stars: Int?=null,
    val followers: Int?=null
)