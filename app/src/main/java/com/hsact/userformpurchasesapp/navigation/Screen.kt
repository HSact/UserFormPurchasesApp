package com.hsact.userformpurchasesapp.navigation

/**
 * Sealed class representing navigation destinations (screens) in the app.
 *
 * Each object corresponds to a distinct screen route used for navigation.
 *
 * @property route The unique string route identifier for the screen.
 */
internal sealed class Screen(val route: String) {
    /** Screen showing user profile information. */
    object Profile : Screen("profile")
    /** Screen for user registration input. */
    object Registration : Screen("registration")
    /** Screen displaying user's purchases. */
    object Purchases : Screen("purchases")
}