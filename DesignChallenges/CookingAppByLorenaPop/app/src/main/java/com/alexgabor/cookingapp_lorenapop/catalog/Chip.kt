package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
) {
    Row(modifier = modifier
        .semantics(mergeDescendants = true) { }
        .clip(CircleShape)
        .background(AppTheme.colors.accentVariant)
        .padding(12.dp)) {
        Image(painter = painter, contentDescription = null, colorFilter = ColorFilter.tint(AppTheme.colors.accent), modifier = Modifier
            .align(CenterVertically)
            .size(16.dp))
        Text(text = text, style = AppTheme.typography.itemCount, modifier = Modifier
            .align(CenterVertically)
            .padding(start = 4.dp))
    }
}