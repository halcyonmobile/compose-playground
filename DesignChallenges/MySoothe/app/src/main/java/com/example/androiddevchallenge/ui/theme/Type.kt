package com.example.androiddevchallenge.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

// Set of Material typography styles to start with
val typography = Typography(
    h1 = TextStyle(fontFamily = FontFamily(Font(R.font.kulim_park_light)), fontSize = 28.sp, letterSpacing = 1.15.sp),
    h2 = TextStyle(fontFamily = FontFamily(Font(R.font.kulim_park_regular)), fontSize = 15.sp, letterSpacing = 1.15.sp),
    h3 = TextStyle(fontFamily = FontFamily(Font(R.font.lato_bold)), fontSize = 14.sp, letterSpacing = 0.sp),
    body1 = TextStyle(fontFamily = FontFamily(Font(R.font.lato_regular)), fontSize = 14.sp, letterSpacing = 0.sp),
    button = TextStyle(fontFamily = FontFamily(Font(R.font.lato_bold)), fontSize = 14.sp, letterSpacing = 1.15.sp),
    caption = TextStyle(fontFamily = FontFamily(Font(R.font.kulim_park_regular)), fontSize = 12.sp, letterSpacing = 1.15.sp),
)
