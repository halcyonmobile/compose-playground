package com.halcyonmobile.composecomponents.component.swipe_to_refresh_with_offset

import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SwipeToRefreshWithOffset() {
    SwipeRefreshSheet(backDropContent = { BackDropContent() },
        loadingIndicator = { LoadingContent() }) {
        itemsIndexed((1..10).map { it }) { index, item ->
            Spacer(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Accent, Shape)
            )
        }
    }
}


private fun LazyListState.isScrolledToTheEnd(): Boolean {
    val lastItem = layoutInfo.visibleItemsInfo.lastOrNull()
    return lastItem == null || lastItem.size + lastItem.offset <= layoutInfo.viewportEndOffset
}

@Composable
fun SwipeRefreshSheet(
    backDropContent: @Composable () -> Unit,
    loadingIndicator: @Composable () -> Unit,
    sheetContent: LazyListScope.() -> Unit,
) {
    var offsetLimit by remember { mutableStateOf(0f) }
    var scrollOffset: Float by remember { mutableStateOf(0f) }
    val verticalOffset by remember { derivedStateOf { scrollOffset.coerceIn(offsetLimit, 0f) } }
    var refreshOffset by remember { mutableStateOf(0f) }
    val cornerFactor by remember {
        derivedStateOf {
            (verticalOffset - offsetLimit).coerceIn(
                0f,
                100f
            ) / 100f
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val scrollstate = rememberScrollableState { delta ->
        if (delta > 0 && verticalOffset == 0f && listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
            scrollOffset = 0f
            refreshOffset += delta * (1 - (refreshOffset / 300f)).coerceAtMost(.5f)
            return@rememberScrollableState 0f
        }
        if (delta < 0 && refreshOffset > 0f) {
            refreshOffset += delta
            return@rememberScrollableState delta
        }
        if (delta < 0 && verticalOffset == offsetLimit && listState.isScrolledToTheEnd()) {
            return@rememberScrollableState 0f
        }
        scrollOffset += delta
        if (scrollOffset in offsetLimit..0f) {
            coroutineScope.launch { listState.scrollToItem(0) }
        }
        if (verticalOffset <= offsetLimit || verticalOffset >= 0f) {
            coroutineScope.launch { listState.scrollBy(-delta) }
        }
        delta
    }

    LaunchedEffect(key1 = scrollstate.isScrollInProgress) {
        if (refreshOffset > 0f && !scrollstate.isScrollInProgress) {
            if (refreshOffset > 200f) {
                animate(refreshOffset, 200f, animationSpec = tween()) { value, velocity ->
                    refreshOffset = value
                }
                delay(1000)
            }
            animate(refreshOffset, 0f, animationSpec = tween()) { value, velocity ->
                refreshOffset = value
            }
        }
    }
    Layout(
        modifier = Modifier
            .scrollable(
                orientation = Orientation.Vertical,
                state = scrollstate,
            ),
        content = {
            backDropContent()
            Box {
                if (refreshOffset > 100f) {
                    loadingIndicator()
                }
            }
            Box(
                Modifier
                    .graphicsLayer(translationY = verticalOffset.coerceIn(offsetLimit, 0f))
                    .graphicsLayer(translationY = refreshOffset.coerceAtLeast(0f))
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(
                            topStart = 16.dp * cornerFactor,
                            topEnd = 16.dp * cornerFactor
                        )
                    )
                    .background(AccentVariant)
            ) {
                LazyColumn(
                    state = listState,
                    userScrollEnabled = false
                ) {
                    sheetContent()
                }
            }
        },
    ) { measurables, constraints ->
        assert(measurables.size == 3)
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        offsetLimit = -placeables[0].height.toFloat()
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables[0].placeRelative(x = 0, y = 0)
            placeables[1].placeRelative(x = 0, y = placeables[0].height)
            placeables[2].placeRelative(x = 0, y = placeables[0].height)
        }
    }
}

@Composable
private fun RowItems() {
    LazyRow(Modifier.fillMaxWidth()) {
        items(20) {
            Spacer(
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .background(Accent, Shape)
            )
        }
    }
}

@Composable
private fun BackDropContent() {
    Column(Modifier.statusBarsPadding()) {
        RowItems()
    }
}

@Composable
private fun LoadingContent() {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(8.dp), contentAlignment = Alignment.Center
    ) { CircularProgressIndicator() }
}

private val Accent = Color(0xffccd5ae)
private val AccentVariant = Color(0xffe9edc9)
private val Shape = RoundedCornerShape(8.dp)