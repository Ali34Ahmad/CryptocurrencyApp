package com.example.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksExtended(
    val url: String?=null,
    val type: String?=null,
    val stats: Stats?=null
)