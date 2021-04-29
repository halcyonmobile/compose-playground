package com.halcyonmobile.composecomponents.component.expandable_list_items

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

private data class Item(
    val text: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
    val items: List<String> = listOf("Item 1", "Item 2", "Item 3"),
    val expanded: Boolean = false,
)

private val initialListItems = (1..10).map { Item() }

@Composable
fun ExpandableItemsList() {
    val listItems = remember { mutableStateOf(initialListItems) } // this state should be provided by a view model

    LazyColumn(modifier = Modifier.fillMaxSize(), content = {
        item { Spacer(modifier = Modifier.statusBarsPadding()) }
        itemsIndexed(listItems.value) { index, item ->

            Surface(shape = RoundedCornerShape(16.dp),
                elevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)) {

                Column(Modifier
                    .clickable {  // clickable also adds a ripple so it needs to be before padding
                        listItems.value = listItems.value.updateList(index) // this event should be sent up and the list updated in view model
                    }
                    .padding(16.dp)) {

                    Text(item.text)

                    AnimatedVisibility(item.expanded) {
                        Column {
                            item.items.forEach {
                                Text(it, modifier = Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.navigationBarsPadding()) }
    })
}

private fun List<Item>.updateList(index: Int) = this.mapIndexed { i, item -> if (index == i) item.copy(expanded = !item.expanded) else item }