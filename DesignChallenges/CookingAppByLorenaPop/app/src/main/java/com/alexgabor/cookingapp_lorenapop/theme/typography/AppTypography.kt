package com.alexgabor.cookingapp_lorenapop.theme.typography

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.alexgabor.cookingapp_lorenapop.R
import com.alexgabor.cookingapp_lorenapop.theme.color.AppColors
import com.alexgabor.cookingapp_lorenapop.theme.color.appColors

val LocalTypography = staticCompositionLocalOf { appTypography }

val appTypography get() = AppTypography(appColors)

val appFontFamily get() = FontFamily(
    fonts = listOf(
        Font(resId = R.font.telegraf_bold,
            weight = FontWeight.Bold,
            style = FontStyle.Normal),
        Font(resId = R.font.telegraf_regular,
            weight = FontWeight.Normal,
            style = FontStyle.Normal),
        Font(resId = R.font.telegraf_ultrabold,
            weight = FontWeight.ExtraBold,
            style = FontStyle.Normal),
    )
)

@Immutable
data class AppTypography(
    val colors: AppColors,
    val title: TextStyle = TextStyle(colors.textOnSurface,
        32.sp,
        lineHeight = 32.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal),
    val titleTall: TextStyle = TextStyle(colors.textOnSurface,
        32.sp,
        lineHeight = 40.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal),
    val subtitle: TextStyle = TextStyle(colors.textOnSurface,
        24.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal),
    val subtitleTall: TextStyle = TextStyle(colors.textOnSurface,
        24.sp,
        lineHeight = 32.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal),
    val itemName: TextStyle = TextStyle(colors.textOnSurface,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center),
    val itemCount: TextStyle = TextStyle(colors.accent,
        14.sp,
        lineHeight = 20.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center),
    val bodyNormalOnSurface: TextStyle = TextStyle(colors.textOnSurface,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal),
    val bodyBoldOnSurface: TextStyle = TextStyle(colors.accent,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal),
    val bodyNormalOnAccent: TextStyle = TextStyle(colors.textOnAccent,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal),
    val bodyBoldOnAccent: TextStyle = TextStyle(colors.textOnAccent,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal),
    val button: TextStyle = TextStyle(colors.textOnSurfaceLight,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal),
    val flatButton: TextStyle = TextStyle(colors.accent,
        16.sp,
        lineHeight = 24.sp,
        fontFamily = appFontFamily,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal,
        textAlign = TextAlign.Center),
)