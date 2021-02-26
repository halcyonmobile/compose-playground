package com.alexgabor.cookingapp_lorenapop.catalog

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme

@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    text: @Composable () -> Unit,
    indicatorModifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
) {
    val background = AppTheme.colors.surfaceVariant
    val accent = AppTheme.colors.accent
    Column(modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            text()
            Box(indicatorModifier.fillMaxWidth()) {
                if (state.layoutInfo.totalItemsCount > 0) {
                    val itemSize = state.layoutInfo.visibleItemsInfo.first().size // Assumes same size for all items
                    val totalSize = state.layoutInfo.totalItemsCount * itemSize
                    val viewportFraction = (state.layoutInfo.viewportEndOffset - state.layoutInfo.viewportStartOffset).toFloat() / totalSize
                    val offset = (state.firstVisibleItemIndex * itemSize + state.firstVisibleItemScrollOffset).toFloat() / totalSize

                    if (viewportFraction < 1) {
                        Canvas(modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .width(AppTheme.dimens.scrollBarLength)
                            .height(AppTheme.dimens.scrollBarThickness), onDraw = {
                            drawRoundRect(background, cornerRadius = CornerRadius(size.height, size.height))
                            drawRoundRect(accent,
                                topLeft = Offset(size.width * offset, 0f),
                                size = Size((size.width * viewportFraction).coerceAtLeast(size.height), size.height),
                                cornerRadius = CornerRadius(size.height, size.height))
                        })
                    }
                }
            }
        }
        LazyRow(state = state,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            content = content)
    }
}