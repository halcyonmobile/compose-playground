package com.halcyonmobile.posterweather.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimens = staticCompositionLocalOf { Dimens() }

data class Dimens(
    val borderWidth: Dp = 1.dp,
    val screenPadding: Dp = 8.dp,
    val toolbarPadding: Dp = 16.dp,
    val contentPaddingVertical: Dp = 20.dp,
    val contentPaddingHorizontal: Dp = 24.dp,
    val dividerWidth: Dp = 8.dp,
    val skyIconSize: Dp = 24.dp,
    val temperatureSpacing: Dp = 24.dp,
)