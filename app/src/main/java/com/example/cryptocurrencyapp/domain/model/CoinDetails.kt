package com.example.cryptocurrencyapp.domain.model

import com.example.cryptocurrencyapp.data.remote.dto.Tag
import com.example.cryptocurrencyapp.data.remote.dto.TeamMember

data class CoinDetails(
    val id: String,
    val name: String?=null,
    val symbol: String?=null,
    val isActive:Boolean?=null,
    val description: String?=null,
    val rank:Int?=null,
    val tags:List<String>?=null,
    val team:List<TeamMember>?=null,
)