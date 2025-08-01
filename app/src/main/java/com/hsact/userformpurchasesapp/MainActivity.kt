package com.hsact.userformpurchasesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hsact.userformpurchasesapp.ui.screens.MainScreen
import com.hsact.userformpurchasesapp.ui.theme.UserFormPurchasesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserFormPurchasesAppTheme {
                    MainScreen()
                }
        }
    }
}