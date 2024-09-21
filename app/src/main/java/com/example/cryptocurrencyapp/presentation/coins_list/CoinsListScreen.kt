package com.example.cryptocurrencyapp.presentation.coins_list

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.presentation.coin_details.CoinDetailsUiState
import com.example.cryptocurrencyapp.presentation.navigation.Destination
import com.example.cryptocurrencyapp.presentation.coins_list.components.CoinsListItem
import com.example.cryptocurrencyapp.presentation.ui.theme.spacing

@Composable
fun CoinsListScreen(
    navController: NavController,
    uiState: CoinsListUiState,
    onGetCoinsEvent:()->Unit,
) {
    LazyColumn(modifier=Modifier.fillMaxSize()) {
        items(uiState.coins){ coin->
            CoinsListItem(
                coin = coin,
                onItemClick = {
                    navController.navigate(Destination.CoinDetailDestination.route + "/${coin.id}")
                }
            )
        }
    }
    if (uiState.errorMessage.isNotBlank()){
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
                onGetCoinsEvent()
            }) {
                Text(text =stringResource(id = R.string.retry))
            }
        }
    }
    if (uiState.isLoading){
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
            CircularProgressIndicator(modifier=Modifier.align(Alignment.CenterHorizontally))
        }
    }

}