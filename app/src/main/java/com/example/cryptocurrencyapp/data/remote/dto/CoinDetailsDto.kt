package com.example.cryptocurrencyapp.data.remote.dto


import com.example.cryptocurrencyapp.domain.model.CoinDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDetailsDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    @SerialName("is_new")
    val isNew: Boolean,
    @SerialName("is_active")
    val isActive: Boolean,
    val contract: String?=null,
    val platform: String?=null,
    val type: String?=null,
    val logo: String?=null,
    val tags: List<Tag>?=null,
    val team: List<TeamMember>?=null,
    val description: String?=null,
    val message: String?=null,
    @SerialName("open_source")
    val openSource: Boolean?=null,
    @SerialName("started_at")
    val startedAt: String?=null,
    @SerialName("development_status")
    val developmentStatus: String?=null,
    @SerialName("hardware_wallet")
    val hardwareWallet: Boolean?=null,
    @SerialName("proof_type")
    val proofType: String?=null,
    @SerialName("org_structure")
    val orgStructure: String?=null,
    @SerialName("hash_algorithm")
    val hashAlgorithm: String?=null,
    val links: Links?=null,
    @SerialName("links_extended")
    val linksExtended: List<LinksExtended>?=null,
    val whitepaper: Whitepaper?=null,
    @SerialName("first_data_at")
    val firstDataAt: String?=null,
    @SerialName("last_data_at")
    val lastDataAt: String?=null,
)

fun CoinDetailsDto.toCoinDetails(): CoinDetails =
    CoinDetails(
        id = this.id,
        name = this.name,
        symbol = this.symbol,
        isActive = this.isActive,
        description = this.description,
        rank = this.rank,
        tags =  this.tags?.map { it.name },
        team = this.team
    )
