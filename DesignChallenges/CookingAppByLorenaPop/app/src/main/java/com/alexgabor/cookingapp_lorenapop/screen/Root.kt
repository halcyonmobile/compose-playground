package com.alexgabor.cookingapp_lorenapop.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexgabor.cookingapp_lorenapop.screen.home.HomeScreen
import com.alexgabor.cookingapp_lorenapop.screen.recipe.RecipeScreen

object Destination {
    const val Home = "home"
    const val Recipe = "recipe"
}

@Composable
fun Root() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Destination.Home) {
        composable(Destination.Home) { HomeScreen(navController) }
        composable(Destination.Recipe) { RecipeScreen(navController) }
    }
}