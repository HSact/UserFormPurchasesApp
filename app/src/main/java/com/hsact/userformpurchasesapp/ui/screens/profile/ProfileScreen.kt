package com.hsact.userformpurchasesapp.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hsact.userformpurchasesapp.R

/**
 * Composable screen that displays user profile information and settings.
 *
 * Shows user's name and surname (or "Unknown" if not available), and a list of profile actions,
 * including navigation to purchases, biometric toggle, language selection, and registration.
 *
 * @param onRegisterClick Callback invoked when the user clicks on the registration card.
 * @param onPurchasesClick Callback invoked when the user clicks on the "My Purchases" card.
 * @param scrollBehavior Scroll behavior applied to the top app bar.
 * @param viewModel ViewModel that provides the profile state and handles user actions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    onRegisterClick: () -> Unit,
    onPurchasesClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    viewModel: ProfileViewModel = hiltViewModel<ProfileViewModel>()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {
        if (uiState.name.isEmpty() || uiState.surname.isEmpty()) {
            Text(
                text = stringResource(R.string.unknown),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.Start)
            )
        } else {
            Text(
                text = uiState.name,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 4.dp)
                    .align(Alignment.Start)
            )
            Text(
                text = uiState.surname,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 24.dp)
                    .align(Alignment.Start)
            )
        }
        ProfileCard(
            title = stringResource(R.string.my_purchases),
            onClick = onPurchasesClick
        )
        ProfileCard(
            title = stringResource(R.string.email),
            subtitle = uiState.email,
            warning = stringResource(R.string.confirmation_required)
        )
        ProfileCard(
            title = stringResource(R.string.biometric_auth),
            trailing = {
                Switch(
                    checked = uiState.isBiometricEnabled,
                    onCheckedChange = { viewModel.switchBiometric() }
                )
            },
            onClick = { viewModel.switchBiometric() }
        )
        ProfileCard(
            title = stringResource(R.string.change_pin)
        )
        ProfileCard(
            title = stringResource(R.string.bank_clients_registration),
            onClick = onRegisterClick
        )
        ProfileCard(
            title = stringResource(R.string.language),
            trailing = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = uiState.language,
                        fontSize = 14.sp,
                        color = Color.LightGray,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                }
            }
        )
    }
}

/**
 * Reusable composable card for profile items in the profile screen.
 *
 * Can display a title, an optional subtitle and warning message,
 * and an optional trailing composable (e.g., icon or switch).
 * If [onClick] is provided, the card becomes clickable.
 *
 * @param title Main title of the card.
 * @param subtitle Optional subtitle displayed below the title.
 * @param warning Optional warning message shown in red below the subtitle.
 * @param trailing Optional composable displayed at the end of the row (e.g., switch or arrow).
 * @param onClick Optional click handler for the entire card.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileCard(
    title: String,
    subtitle: String? = null,
    warning: String? = null,
    trailing: @Composable (() -> Unit)? = {
        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
    },
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .then(
                if (onClick != null) Modifier.clickable { onClick() } else Modifier
            ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, fontSize = 16.sp)

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (subtitle != null || warning != null) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        subtitle?.let {
                            Text(text = it, fontSize = 14.sp)
                        }
                        warning?.let {
                            Text(text = it, fontSize = 12.sp, color = Color.Red)
                        }
                    }
                }
                trailing?.invoke()
            }
        }
    }
}