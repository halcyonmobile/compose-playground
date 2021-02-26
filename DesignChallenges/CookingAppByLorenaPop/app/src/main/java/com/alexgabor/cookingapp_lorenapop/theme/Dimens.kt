package com.alexgabor.cookingapp_lorenapop.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimens = staticCompositionLocalOf { Dimens() }

val appDimens = Dimens()

data class Dimens(
    val screenPadding: Dp = 32.dp,
    val toolbarHeight: Dp = 32.dp,
    val iconSpacing: Dp = 16.dp,
    val buttonHeight: Dp = 64.dp,
    val buttonElevation: Dp = 16.dp,
    val bannerHeight: Dp = 256.dp,
    val bannerButtonPadding: Dp = 8.dp,
    val carouselItemSpacing: Dp = 28.dp,
    val categoryItemWidth: Dp = 142.dp,
    val categoryItemHeight: Dp = 180.dp,
    val categoryIconSize: Dp = 48.dp,
    val scrollBarThickness: Dp = 4.dp,
    val scrollBarLength: Dp = 92.dp,
)