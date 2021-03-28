package com.example.androiddevchallenge

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.Navigator

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Navigator()
    }
}
