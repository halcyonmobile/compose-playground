package com.halcyonmobile.posterweather.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.halcyonmobile.posterweather.R

internal val LocalTypography = staticCompositionLocalOf { Typography(appColors) }

val appFontFamily
    get() = FontFamily(
        fonts = listOf(
            Font(resId = R.font.inter_bold,
                weight = FontWeight.Bold,
                style = FontStyle.Normal),
            Font(resId = R.font.inter_regular,
                weight = FontWeight.Normal,
                style = FontStyle.Normal),
        )
    )

data class Typography(
    val colors: Colors,
    val regular: TextStyle = TextStyle(
        color = colors.ink,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        letterSpacing = 2.sp
    ),
    val bold: TextStyle = TextStyle(
        color = colors.ink,
        fontSize = 20.sp,
        lineHeight = 25.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
    ),
    val boldInverse: TextStyle = bold.copy(color = colors.paper),
    val big: TextStyle = TextStyle(
        color = colors.ink,
        fontSize = 200.sp,
        lineHeight = 200.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
    ),
)