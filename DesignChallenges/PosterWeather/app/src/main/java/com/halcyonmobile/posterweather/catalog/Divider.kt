package com.halcyonmobile.posterweather.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.halcyonmobile.posterweather.theme.AppTheme

@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    width: Dp = 1.dp,
) {
    Spacer(modifier = Modifier
        .fillMaxWidth()
        .height(width)
        .background(AppTheme.colors.ink)
        .then(modifier))
}

@Composable
fun VerticalDivider(
    modifier: Modifier = Modifier,
    width: Dp = 1.dp,
) {
    Spacer(modifier = Modifier
        .width(width)
        .background(AppTheme.colors.ink)
        .then(modifier))
}