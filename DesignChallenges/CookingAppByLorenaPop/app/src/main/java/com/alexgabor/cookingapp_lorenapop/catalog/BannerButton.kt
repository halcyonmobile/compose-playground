package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.alexgabor.cookingapp_lorenapop.R
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme

@Composable
fun InternalBannerButton(
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.large,
    elevation: Dp = AppTheme.dimens.buttonElevation,
    thumb: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.(thumbSize: Dp) -> Unit,
) {
    val contentSize = remember { mutableStateOf(IntSize(0, 0)) }
    val thumbSize = contentSize.value.height

    Surface(modifier = modifier
        .onSizeChanged { intSize -> contentSize.value = intSize },
        shape = shape,
        elevation = elevation) {
        Box {
            content(this, with(LocalDensity.current) { thumbSize.toDp() })
            thumb(this)
        }
    }
}

@Composable
fun BannerButton(
    modifier: Modifier,
    content: @Composable BoxScope.(thumbSize: Dp) -> Unit,
) {
    InternalBannerButton(modifier,
        thumb = {
            Box(modifier = Modifier
                .padding(AppTheme.dimens.bannerButtonPadding)
                .size(AppTheme.dimens.buttonHeight - AppTheme.dimens.bannerButtonPadding * 2)
                .clip(CircleShape)
                .background(AppTheme.colors.accent)
                .align(Alignment.CenterEnd)) {
                Image(painterResource(R.drawable.ic_arrow_forward), null, modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Center))
            }
        },
        content = content)

}