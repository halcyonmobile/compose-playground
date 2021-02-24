package com.alexgabor.cookingapp_lorenapop.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableContract
import androidx.compose.runtime.Providers
import androidx.compose.runtime.ReadOnlyComposable
import com.alexgabor.cookingapp_lorenapop.theme.color.LocalColors
import com.alexgabor.cookingapp_lorenapop.theme.color.AppColors
import com.alexgabor.cookingapp_lorenapop.theme.color.appColors
import com.alexgabor.cookingapp_lorenapop.theme.shape.LocalShapes
import com.alexgabor.cookingapp_lorenapop.theme.shape.AppShapes
import com.alexgabor.cookingapp_lorenapop.theme.shape.appShapes
import com.alexgabor.cookingapp_lorenapop.theme.typography.LocalTypography
import com.alexgabor.cookingapp_lorenapop.theme.typography.AppTypography
import com.alexgabor.cookingapp_lorenapop.theme.typography.appTypography

object AppTheme {
    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

@Composable
fun CookingTheme(content: @Composable () -> Unit) {
    Providers(
        LocalColors provides appColors,
        LocalDimens provides appDimens,
        LocalTypography provides appTypography,
        LocalShapes provides appShapes,
    ) {
        MaterialTheme(
            content = content
        )
    }
}