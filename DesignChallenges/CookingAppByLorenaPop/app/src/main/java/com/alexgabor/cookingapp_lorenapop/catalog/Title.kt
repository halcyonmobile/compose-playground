package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme

@Composable
fun Title(text: String, modifier: Modifier) {
    Text(text,
        style = AppTheme.typography.title,
        modifier = modifier)
}