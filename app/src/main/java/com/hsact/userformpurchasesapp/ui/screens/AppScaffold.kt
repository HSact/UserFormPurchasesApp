package com.hsact.userformpurchasesapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hsact.userformpurchasesapp.R
import com.hsact.userformpurchasesapp.navigation.Screen
import com.hsact.userformpurchasesapp.ui.components.CommonTopBar
import com.hsact.userformpurchasesapp.ui.screens.profile.ProfileScreen
import com.hsact.userformpurchasesapp.ui.screens.purchases.PurchasesScreen
import com.hsact.userformpurchasesapp.ui.screens.registration.RegistrationScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val (title, showBackButton) = when (currentDestination?.route) {
        Screen.Profile.route -> stringResource(R.string.profile) to false
        Screen.Registration.route -> stringResource(R.string.bank_clients_registration) to true
        Screen.Purchases.route -> stringResource(R.string.my_purchases) to true
        else -> "" to false
    }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        topBar = {
            CommonTopBar(
                title = title,
                showBackButton = showBackButton,
                scrollBehavior = scrollBehavior,
                onBackClick = { navController.popBackStack() }
            )
        },
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Profile.route,
            modifier = Modifier.padding(innerPadding).padding(horizontal = 24.dp)
        ) {
            composable(Screen.Profile.route) {
                ProfileScreen(
                    onRegisterClick = {
                        navController.navigate(Screen.Registration.route)
                    },
                    onPurchasesClick = {
                        navController.navigate(Screen.Purchases.route)
                    },
                    scrollBehavior = scrollBehavior
                )
            }
            composable(Screen.Registration.route) {
                RegistrationScreen(onFinish = {
                    navController.popBackStack()
                })
            }
            composable(Screen.Purchases.route) {
                PurchasesScreen()
            }
        }
    }
}