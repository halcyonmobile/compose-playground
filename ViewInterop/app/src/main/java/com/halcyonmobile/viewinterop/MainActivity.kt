package com.halcyonmobile.viewinterop

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            InteropApp()
        }
    }
}

@Composable
private fun InteropApp() {
    ProvideWindowInsets {
        InteropExamples()
    }
}

@Composable
fun InteropExamples() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.statusBarsPadding()) {
            Text(
                text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(16.dp)
            )
        }

        val context = LocalContext.current
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(Examples) { component ->
                Column(Modifier
                    .fillMaxWidth()
                    .clickable { context.startActivity(Intent(context, component.activity)) }
                    .padding(horizontal = 16.dp, vertical = 8.dp)) {
                    Text(component.title, style = MaterialTheme.typography.subtitle1)
                    Text(component.subtitle, style = MaterialTheme.typography.caption)
                }
            }
            item { Spacer(modifier = Modifier.navigationBarsPadding()) }
        }
    }
}
