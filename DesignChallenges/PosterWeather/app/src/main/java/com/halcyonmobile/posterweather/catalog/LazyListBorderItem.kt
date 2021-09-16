package com.halcyonmobile.posterweather.catalog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.zIndex

fun LazyListScope.borderItem(key: Any? = null, top: Boolean = false, bottom: Boolean = false, content: @Composable LazyItemScope.() -> Unit) {
    item(key) {
        Box(Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
            if (top) HorizontalDivider()
            VerticalDivider(Modifier
                .fillMaxHeight()
                .align(Alignment.TopStart))
            Box(Modifier.zIndex(1f)) {
                content()
            }
            VerticalDivider(Modifier
                .fillMaxHeight()
                .align(Alignment.TopEnd))
            if (bottom) HorizontalDivider(modifier = Modifier.align(Alignment.BottomStart))
        }
    }
}