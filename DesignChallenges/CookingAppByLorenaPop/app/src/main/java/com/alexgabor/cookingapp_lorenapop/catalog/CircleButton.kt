package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme

@Composable
fun CircleButton(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(Modifier
        .size(AppTheme.dimens.categoryIconSize)
        .clip(CircleShape)
        .background(AppTheme.colors.surface)
        .padding(8.dp)
        .then(modifier),
        contentAlignment = Alignment.Center) {
        content()
    }
}