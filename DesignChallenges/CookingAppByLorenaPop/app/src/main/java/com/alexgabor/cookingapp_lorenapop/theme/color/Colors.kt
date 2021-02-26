package com.alexgabor.cookingapp_lorenapop.theme.color

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { appColors }

val appColors: AppColors = AppColors()

@Immutable
data class AppColors(
    val accent: Color = Color(0xFFD8003E),
    val accentVariant: Color = Color(0x0DD8003E),
    val surface: Color = Color(0xFFFFFFFF),
    val surfaceVariant: Color = Color(0x080D1C2E),
    val textOnSurface: Color = Color(0xFF0D1C2E),
    val textOnSurfaceLight: Color = Color(0xA30D1C2E),
    val textOnAccent: Color = Color(0xFFFFFFFF),
)