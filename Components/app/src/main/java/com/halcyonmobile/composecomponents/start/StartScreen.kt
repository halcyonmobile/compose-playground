package com.halcyonmobile.composecomponents.start

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.halcyonmobile.composecomponents.Component
import com.halcyonmobile.composecomponents.R
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun StartScreen(components: List<Component>, onClick: (index: Int, component: Component) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.statusBarsPadding()) {
            Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp))
        }
        LazyColumn(modifier = Modifier.fillMaxWidth(), content = {
            itemsIndexed(components) { index, component ->
                Column(Modifier.fillMaxWidth()
                    .clickable { onClick(index, component) }
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp)) {
                    Text(component.title, style = MaterialTheme.typography.subtitle1)
                    Text(component.subtitle, style = MaterialTheme.typography.caption)
                }
            }
            item {
                Spacer(modifier = Modifier.navigationBarsPadding())
            }
        })
    }
}
