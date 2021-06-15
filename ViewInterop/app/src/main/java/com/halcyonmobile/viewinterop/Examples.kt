package com.halcyonmobile.viewinterop

import androidx.activity.ComponentActivity
import com.halcyonmobile.viewinterop.example.android_view.AndroidViewActivity
import com.halcyonmobile.viewinterop.example.compose_view.ComposeViewActivity
import com.halcyonmobile.viewinterop.example.dynamic_compose_view.DynamicComposeViewActivity

data class Example(val title: String, val subtitle: String, val activity: Class<out ComponentActivity>)

val Examples = listOf(
    Example("ComposeView", "Add compose in XML", ComposeViewActivity::class.java),
    Example("ComposeView added dynamically", "Add compose dynamically in XML", DynamicComposeViewActivity::class.java),
    Example("AndroidView", "", AndroidViewActivity::class.java),
)