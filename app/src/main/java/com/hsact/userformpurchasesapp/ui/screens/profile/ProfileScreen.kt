package com.hsact.userformpurchasesapp.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsact.userformpurchasesapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onRegisterClick: () -> Unit,
    onPurchasesClick: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()
) {
    val scrollState = rememberScrollState()
    val canScroll = remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    LaunchedEffect(scrollState.maxValue) {
        canScroll.value = scrollState.maxValue > 0
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onRegisterClick) {
            Text(stringResource(R.string.registration))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
        Button(onClick = onPurchasesClick) {
            Text(stringResource(R.string.my_purchases))
        }
    }
}