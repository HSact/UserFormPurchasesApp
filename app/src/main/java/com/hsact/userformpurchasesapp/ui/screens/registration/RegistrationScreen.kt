package com.hsact.userformpurchasesapp.ui.screens.registration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hsact.userformpurchasesapp.R

@Composable
fun RegistrationScreen (
    onFinish: () -> Unit,
    viewModel: RegistrationViewModel = hiltViewModel<RegistrationViewModel>()
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.bank_clients_registration))
        Button(onClick = onFinish) {
            Text(stringResource(R.string.button_continue))
        }
    }
}