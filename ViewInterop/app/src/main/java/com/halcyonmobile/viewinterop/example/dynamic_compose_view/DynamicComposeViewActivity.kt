package com.halcyonmobile.viewinterop.example.dynamic_compose_view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.halcyonmobile.viewinterop.R
import com.halcyonmobile.viewinterop.databinding.ActivityDynamicComposeViewBinding

class DynamicComposeViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDynamicComposeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.content) {
            addView(ComposeView(this@DynamicComposeViewActivity).apply {
                id = R.id.compose_view_1
                setContent { ComposeContent("You can add ComposeView dynamically in XML") }
            })
            addView(ComposeView(this@DynamicComposeViewActivity).apply {
                id = R.id.compose_view_2
                setContent { ComposeContent("Make sure to give it a unique ID") }
            })
            addView(ComposeView(this@DynamicComposeViewActivity).apply {
                id = R.id.compose_view_3
                setContent { DocLink() }
            })
        }
    }
}

@Composable
fun ComposeContent(text: String) {
    MaterialTheme {
        Box(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = text, style = MaterialTheme.typography.body1)
        }
    }
}

@Composable
fun DocLink() {
    val uriHandler = LocalUriHandler.current
    MaterialTheme {
        Box(
            Modifier
                .clickable { uriHandler.openUri("https://developer.android.com/jetpack/compose/interop/interop-apis#compose-in-views") }
                .padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(text = "See docs", style = MaterialTheme.typography.body1.copy(textDecoration = TextDecoration.Underline))
        }
    }
}