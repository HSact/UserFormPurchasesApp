package com.hsact.userformpurchasesapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

/**
 * A reusable top app bar component with optional back button and scroll behavior.
 *
 * @param title Title text to display in the app bar.
 * @param showBackButton Whether to display the back arrow icon. Default is `true`.
 * @param scrollBehavior Scroll behavior to attach to the top app bar (e.g. for collapsing).
 * @param onBackClick Callback invoked when the back button is clicked. Default is no-op.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    title: String,
    showBackButton: Boolean = true,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        scrollBehavior = scrollBehavior
    )
}