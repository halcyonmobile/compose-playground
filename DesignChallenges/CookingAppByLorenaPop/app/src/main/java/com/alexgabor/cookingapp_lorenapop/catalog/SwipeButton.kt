package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
private fun InternalSwipeButton(
    modifier: Modifier = Modifier,
    threshold: Float = .5f,
    shape: Shape = AppTheme.shapes.large,
    elevation: Dp = AppTheme.dimens.buttonElevation,
    thumb: @Composable BoxScope.(thumbSize: Dp) -> Unit,
    content: @Composable BoxScope.(offset: IntOffset) -> Unit,
) {
    val position = remember { mutableStateOf(0f) }
    val animateBack = remember { Animatable(initialValue = position.value) }
    val isDragging = remember { mutableStateOf(false) }
    val contentSize = remember { mutableStateOf(IntSize(0, 0)) }
    val thumbSize = contentSize.value.height
    val animationScope = rememberCoroutineScope()

    Surface(modifier = modifier
        .onSizeChanged { intSize -> contentSize.value = intSize }
        .draggable(Orientation.Horizontal,
            onDragStarted = {
                isDragging.value = true
            },
            onDragStopped = {
                animationScope.launch {
                    isDragging.value = false
                    animateBack.snapTo(position.value)
                    val destination = if (position.value / contentSize.value.width < threshold) {
                        0f
                    } else {
                        contentSize.value.width.toFloat() - thumbSize
                    }
                    animateBack.animateTo(destination)
                    position.value = destination
                }
            },
            onDrag = { delta ->
                position.value = (position.value + delta).coerceIn(0f, contentSize.value.width.toFloat() - thumbSize)
            }
        ),
        shape = shape,
        elevation = elevation) {
        Box {
            thumb(this, with(LocalDensity.current) { thumbSize.toDp() })
            content(this, IntOffset(if (isDragging.value) position.value.roundToInt() else animateBack.value.roundToInt(), 0))
        }
    }
}

@Composable
fun SwipeButton(
    modifier: Modifier,
) {
    InternalSwipeButton(modifier,
        thumb = { startOffset ->
            Text("Swipe to start >>>",
                Modifier
                    .align(Alignment.Center)
                    .padding(start = startOffset, end = 4.dp)
            )
        }
    ) { offset ->
        Icon(Icons.Filled.ArrowForward, null, tint = Color.White, modifier = Modifier
            .padding(AppTheme.dimens.bannerButtonPadding)
            .preferredSize(AppTheme.dimens.buttonHeight - AppTheme.dimens.bannerButtonPadding * 2)
            .offset { offset }
            .clip(CircleShape)
            .background(AppTheme.colors.accent))
    }
}