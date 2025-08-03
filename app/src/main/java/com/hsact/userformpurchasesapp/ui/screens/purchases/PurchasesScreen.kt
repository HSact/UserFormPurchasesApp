package com.hsact.userformpurchasesapp.ui.screens.purchases

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

/**
 * Composable screen displaying the user's purchases grouped by date.
 *
 * Observes [PurchasesViewModel]'s UI state and shows:
 * - a loading indicator while data is loading,
 * - an error message if loading fails,
 * - or a grouped list of purchases on success.
 *
 * @param viewModel The ViewModel that provides the purchases data.
 */
@Composable
fun PurchasesScreen(
    viewModel: PurchasesViewModel = hiltViewModel<PurchasesViewModel>()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        is PurchasesUiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is PurchasesUiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.message)
            }
        }

        is PurchasesUiState.Success -> {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                state.groupedPurchases.forEach { (date, names) ->
                    item {
                        Text(
                            text = date,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(vertical = 16.dp)
                        )
                    }
                    items(names) { name ->
                        PurchaseCard(name)
                    }
                }
            }
        }
    }
}

/**
 * Displays a single purchase item inside a card.
 *
 * @param name Name or description of the purchase.
 */
@Composable
private fun PurchaseCard(name: String) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}