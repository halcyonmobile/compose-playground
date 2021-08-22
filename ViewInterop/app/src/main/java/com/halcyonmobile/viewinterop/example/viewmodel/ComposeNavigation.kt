package com.halcyonmobile.viewinterop.example.viewmodel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun ComposeNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "page/{index}") {
        composable(
            route = "page/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getInt("index")?.let { index ->
                Page(index = index,
                    onPrev = { navController.navigate("page/${index - 1}") },
                    onNext = { navController.navigate("page/${index + 1}") })
            } ?: Text(text = "Missing index")
        }
    }
}

@Composable
fun Page(index: Int, onPrev: () -> Unit, onNext: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(text = "Navigation Compose: ViewModel is scoped to the destination")
        Text(text = "Page $index")
        /**
         * When calling it here the ViewModel will be scoped to this Navigation destination
         */
        ComposeWithViewModels()

        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(onClick = onPrev, modifier = Modifier.padding(8.dp), enabled = index > 0) {
                Text(text = "Back")
            }
            Button(onClick = onNext, modifier = Modifier.padding(8.dp)) {
                Text(text = "Next")
            }
        }
    }
}