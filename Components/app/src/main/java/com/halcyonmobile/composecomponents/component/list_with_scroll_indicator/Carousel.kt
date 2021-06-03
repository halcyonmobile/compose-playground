package com.halcyonmobile.composecomponents.component.list_with_scroll_indicator


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding


/**
 * Acts as a wrapper over [LazyRow] and adds a title and a custom scroll indicator.
 *
 * It draws the scroll indicator assuming that all items have the same width.
 *
 * @param modifier The modifier applied to the Carousel
 * @param listState Argument passed to internal [LazyRow]
 * @param contentPadding Argument passed to internal [LazyRow]
 * @param reverseLayout Argument passed to internal [LazyRow]
 * @param horizontalArrangement Argument passed to internal [LazyRow]
 * @param verticalAlignment Argument passed to internal [LazyRow]
 * @param title [Composable] for the title
 * @param indicatorModifier The modifier applied to the Canvas of the scroll indicator
 */
@Composable
fun Carousel(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    horizontalArrangement: Arrangement.Horizontal =
        if (!reverseLayout) Arrangement.Start else Arrangement.End,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    title: @Composable () -> Unit,
    indicatorModifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit,
) {
    val background = Color.LightGray // This color should be provided by the theme
    val indicatorColor = MaterialTheme.colors.primary
    Column(modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            title()
            Box(modifier = Modifier.fillMaxWidth()) {
                if (listState.layoutInfo.totalItemsCount > 0) {
                    val itemSize = listState.layoutInfo.visibleItemsInfo.first().size // Assumes same size for all items
                    val totalSize = listState.layoutInfo.totalItemsCount * itemSize

                    // length of scrollbar based on how much of the total list is visible
                    val viewportFraction = (listState.layoutInfo.viewportEndOffset - listState.layoutInfo.viewportStartOffset).toFloat() / totalSize
                    // how much should the scrollbar be offset
                    val offset = (listState.firstVisibleItemIndex * itemSize + listState.firstVisibleItemScrollOffset).toFloat() / totalSize

                    // scrollbar is only drawn if there's content outside the screen
                    if (viewportFraction < 1) {
                        Canvas(modifier = Modifier.align(Alignment.CenterEnd).then(indicatorModifier), onDraw = {
                            drawRoundRect(background, cornerRadius = CornerRadius(size.height, size.height))
                            drawRoundRect(indicatorColor,
                                topLeft = Offset(size.width * offset, 0f),
                                size = Size((size.width * viewportFraction).coerceAtLeast(size.height), size.height), // Width is coerced in case the list is too long
                                cornerRadius = CornerRadius(size.height, size.height))
                        })
                    }
                }
            }
        }
        LazyRow(state = listState,
            contentPadding = contentPadding,
            reverseLayout = reverseLayout,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            content = content)
    }
}

@Composable
fun ListWithScrollIndicator() {
    Box(modifier = Modifier.systemBarsPadding()) {
        Carousel(
            title = { Text(text = "Label", style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(start = 16.dp)) },
            indicatorModifier = Modifier.size(96.dp, 4.dp).align(Alignment.CenterEnd).padding(end = 16.dp),
            content = {
                items(10) { index ->
                    Box(modifier = Modifier
                        .padding(top = 32.dp)
                        .padding(start = if (index == 0) 16.dp else 8.dp, end = if (index == 9) 16.dp else 8.dp)
                        .size(256.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                        .padding(top = 32.dp, bottom = 24.dp)) {

                    }
                }
            })
    }
}