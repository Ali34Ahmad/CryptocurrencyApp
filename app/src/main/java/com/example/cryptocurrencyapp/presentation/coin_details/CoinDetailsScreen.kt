package com.example.cryptocurrencyapp.presentation.coin_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.presentation.coin_details.components.CoinDetailsHeader
import com.example.cryptocurrencyapp.presentation.coin_details.components.CoinTag
import com.example.cryptocurrencyapp.presentation.coin_details.components.TeamListItem
import com.example.cryptocurrencyapp.presentation.ui.theme.mediumGrayDark
import com.example.cryptocurrencyapp.presentation.ui.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailsScreen(
    uiState:CoinDetailsUiState,
    onGetCoinByIdEvent:(String?)->Unit,
) {
    uiState.coin?.let { coin ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                if (uiState.coin.name != null) {
                    CoinDetailsHeader(coinDetails = coin)
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                }
                if (!uiState.coin.description.isNullOrEmpty()) {
                    Text(
                        text = "${coin.description}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = MaterialTheme.spacing.medium16),
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
                }
                if (!coin.tags.isNullOrEmpty()) {
                    Text(
                        text = stringResource(id = R.string.tags),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.medium16),
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.medium16),
                        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                    ) {
                        coin.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium24))
                }
            }
            if (!coin.team.isNullOrEmpty()) {
                item {
                    Text(
                        text = stringResource(id = R.string.team_members),
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = MaterialTheme.spacing.medium16),
                    )
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                vertical = MaterialTheme.spacing.small,
                                horizontal = MaterialTheme.spacing.medium16
                            ),
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .padding(horizontal = MaterialTheme.spacing.medium16)
                            .background(color = mediumGrayDark),
                    )
                }
            }
        }
    }
    if (uiState.errorMessage.isNotBlank()) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(id = R.string.error_message),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.large48, vertical = MaterialTheme.spacing.medium16),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            OutlinedButton(onClick = {
                onGetCoinByIdEvent(uiState.coinId)
            }) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    } else if (uiState.isLoading) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = R.string.loading),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.large48, vertical = MaterialTheme.spacing.medium16),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium16))
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}