package com.example.cryptocurrencyapp.presentation.coin_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.domain.model.CoinDetails
import com.example.cryptocurrencyapp.presentation.ui.theme.spacing

@Composable
fun CoinDetailsHeader(
    coinDetails: CoinDetails,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium16),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "${coinDetails.rank}. ${coinDetails.name} (${coinDetails.symbol})",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(8f),
        )
        Text(
            text = if (coinDetails.isActive == true) stringResource(R.string.active) else stringResource(R.string.inactive),
            color= if (coinDetails.isActive == true) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(
                Alignment.CenterVertically
            ),
            fontStyle = FontStyle.Italic,
        )
    }
}