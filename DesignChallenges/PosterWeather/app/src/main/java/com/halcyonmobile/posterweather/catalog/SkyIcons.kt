package com.halcyonmobile.posterweather.catalog

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.halcyonmobile.posterweather.theme.AppTheme

@Composable
fun Sunny(
    modifier: Modifier = Modifier,
) {
    val border = with(LocalDensity.current) { AppTheme.dimens.borderWidth.toPx() }
    val ink = AppTheme.colors.ink
    Canvas(modifier = modifier) {
        val offset = this.center + Offset(this.size.width / 10, -this.size.height / 8)
        drawCircle(ink)
        drawCircle(Color(0xffefebe8), center = offset)
        drawCircle(ink,
            center = offset,
            style = Stroke(width = border)
        )
    }
}

@Composable
fun Clouds(
    modifier: Modifier = Modifier,
) {
    val border = with(LocalDensity.current) { AppTheme.dimens.borderWidth.toPx() }
    val ink = AppTheme.colors.ink
    Canvas(modifier = modifier) {
        drawCircle(ink, style = Stroke(width = border))
        drawCircle(
            ink, style = Stroke(width = border),
            radius = size.minDimension / 3,
            center = Offset( 0f, size.height / 3 * 2)
        )
        drawCircle(
            ink,
            radius = size.minDimension / 4,
            center = Offset(size.width, size.height - size.minDimension / 4)
        )
    }
}