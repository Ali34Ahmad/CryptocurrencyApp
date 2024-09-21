package com.example.cryptocurrencyapp.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    val explorer: List<String>?=null,
    val facebook: List<String>?=null,
    val reddit: List<String>?=null,
    @SerialName("source_code")
    val sourceCode: List<String>?=null,
    val website: List<String>?=null,
    val youtube: List<String>?=null
)