package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.alexgabor.cookingapp_lorenapop.R
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme
import kotlin.math.roundToInt
import kotlinx.coroutines.launch


@Composable
private fun InternalSwipeButton(
    modifier: Modifier = Modifier,
    threshold: Float = .5f,
    shape: Shape = AppTheme.shapes.large,
    elevation: Dp = AppTheme.dimens.buttonElevation,
    content: @Composable BoxScope.(thumbSize: Dp) -> Unit,
    thumb: @Composable BoxScope.(offset: IntOffset) -> Unit,
) {
    val position = remember { mutableStateOf(0f) }
    val animateBack = remember { Animatable(initialValue = position.value) }
    val isDragging = remember { mutableStateOf(false) }
    val contentSize = remember { mutableStateOf(IntSize(0, 0)) }
    val thumbSize = contentSize.value.height
    val animationScope = rememberCoroutineScope()
    val draggableState = rememberDraggableState { delta ->
        position.value = (position.value + delta).coerceIn(0f, contentSize.value.width.toFloat() - thumbSize)
    }

    Surface(modifier = modifier
        .onSizeChanged { intSize -> contentSize.value = intSize }
        .draggable(state = draggableState, orientation = Orientation.Horizontal,
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
            }
        ),
        shape = shape,
        elevation = elevation) {
        Box {
            content(this, with(LocalDensity.current) { thumbSize.toDp() })
            thumb(this, IntOffset(if (isDragging.value) position.value.roundToInt() else animateBack.value.roundToInt(), 0))
        }
    }
}

@Composable
fun SwipeButton(
    modifier: Modifier = Modifier,
) {
    InternalSwipeButton(modifier,
        content = { startOffset ->
            Text("Swipe to start >>>", style = AppTheme.typography.button,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = startOffset, end = 4.dp)
            )
        }
    ) { offset ->
        Box(modifier = Modifier
            .padding(AppTheme.dimens.bannerButtonPadding)
            .size(AppTheme.dimens.buttonHeight - AppTheme.dimens.bannerButtonPadding * 2)
            .offset { offset }
            .clip(CircleShape)
            .background(AppTheme.colors.accent)) {
            Image(painterResource(R.drawable.ic_arrow_forward), null, modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center))
        }
    }
}