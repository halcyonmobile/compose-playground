package com.alexgabor.cookingapp_lorenapop.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexgabor.cookingapp_lorenapop.screen.home.HomeScreen
import com.alexgabor.cookingapp_lorenapop.screen.recipe.RecipeScreen
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme

object Destination {
    const val Home = "home"
    const val Recipe = "recipe"
}

@Composable
fun Root() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Destination.Home, modifier = Modifier.background(AppTheme.colors.surface)) {
        composable(Destination.Home) { HomeScreen(navController) }
        composable(Destination.Recipe) { RecipeScreen(navController) }
    }
}