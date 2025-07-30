package com.hsact.userformpurchasesapp.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.hsact.userformpurchasesapp.R

@Composable
fun ProfileScreen(onRegisterClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.profile))
        Button(onClick = onRegisterClick) {
            Text(stringResource(R.string.registration))
        }
    }
}
