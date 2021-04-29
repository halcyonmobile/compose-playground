package com.example.androiddevchallenge.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = white,
    primaryVariant = white,
    secondary = rust300,
//    secondaryVariant = secondary,
    background = gray900,
    surface = white150,
//    error = Color(0xFFCF6679),
    onPrimary = gray900,
    onSecondary = gray900,
    onBackground = taupe100,
    onSurface = white800,
//    onError = Color.Black
)

private val LightColorPalette = lightColors(
    primary = gray900,
    primaryVariant = gray900,
    secondary = rust600,
//    secondaryVariant = secondary,
    background = taupe100,
    surface = white850,
//    error = Color(0xFFCF6679),
    onPrimary = white,
    onSecondary = white,
    onBackground = taupe800,
    onSurface = gray800,
//    onError = Color.Black
)

@Composable
fun MyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
