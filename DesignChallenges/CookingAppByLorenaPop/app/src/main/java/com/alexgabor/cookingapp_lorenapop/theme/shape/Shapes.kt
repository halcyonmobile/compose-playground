package com.alexgabor.cookingapp_lorenapop.theme.shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val LocalShapes = staticCompositionLocalOf { appShapes }

val appShapes get() = AppShapes()

data class AppShapes(
    val standard: CornerBasedShape = RoundedCornerShape(24.dp),
    val bottomSheet: CornerBasedShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    val large: CornerBasedShape = RoundedCornerShape(32.dp)
)