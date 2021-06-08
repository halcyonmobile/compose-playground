package com.halcyonmobile.posterweather.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = staticCompositionLocalOf { appColors }

val appColors: Colors = Colors()

@Immutable
data class Colors(
    val paper: Color = Color(0xffefebe8),
    val ink: Color = Color(0xff1E1E1B),
)