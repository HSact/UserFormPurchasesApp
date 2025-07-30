package com.hsact.userformpurchasesapp.navigation

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Registration : Screen("registration")
    object Purchases : Screen("purchases")
}