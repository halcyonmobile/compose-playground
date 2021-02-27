package com.halcyonmobile.composecomponents

import androidx.compose.runtime.Composable
import com.halcyonmobile.composecomponents.component.expandable_list_items.ExpandableItemsList
import com.halcyonmobile.composecomponents.component.list_with_scroll_indicator.ListWithScrollIndicator


data class Component(val title: String, val subtitle: String, val content: @Composable () -> Unit)

val components = listOf(
    Component("List with scroll indicator", "Horizontal list with same size items and custom scroll indicator") { ListWithScrollIndicator() },
    Component("List with expandable items", "Lazy list with expandable items") { ExpandableItemsList() },
)
