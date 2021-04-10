package com.alexgabor.cookingapp_lorenapop

import androidx.compose.runtime.Composable
import com.alexgabor.cookingapp_lorenapop.screen.home.HomeScreen
import com.alexgabor.cookingapp_lorenapop.theme.CookingTheme
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun CookingApp() {
    CookingTheme {
        ProvideWindowInsets {
            HomeScreen()
        }
    }
}