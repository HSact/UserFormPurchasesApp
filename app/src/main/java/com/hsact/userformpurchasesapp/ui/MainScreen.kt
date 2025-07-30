package com.hsact.userformpurchasesapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hsact.userformpurchasesapp.navigation.Screen
import com.hsact.userformpurchasesapp.ui.profile.ProfileScreen
import com.hsact.userformpurchasesapp.ui.purchases.PurchasesScreen
import com.hsact.userformpurchasesapp.ui.registration.RegistrationScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Profile.route,
        modifier = modifier
    ) {
        composable(Screen.Profile.route) {
            ProfileScreen(onRegisterClick = {
                navController.navigate(Screen.Registration.route)
            })
        }
        composable(Screen.Registration.route) {
            RegistrationScreen(onFinish = {
                navController.navigate(Screen.Purchases.route)
            })
        }
        composable(Screen.Purchases.route) {
            PurchasesScreen()
        }
    }
}