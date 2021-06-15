package com.halcyonmobile.viewinterop

import androidx.activity.ComponentActivity
import com.halcyonmobile.viewinterop.example.android_view.AndroidViewActivity
import com.halcyonmobile.viewinterop.example.compose_view.ComposeViewActivity
import com.halcyonmobile.viewinterop.example.dynamic_compose_view.DynamicComposeViewActivity
import com.halcyonmobile.viewinterop.example.viewbinding.ViewBindingActivity

data class Example(val title: String, val subtitle: String, val activity: Class<out ComponentActivity>)

val Examples = listOf(
    Example("ComposeView", "Add compose in XML", ComposeViewActivity::class.java),
    Example("ComposeView added dynamically", "Add compose dynamically in XML", DynamicComposeViewActivity::class.java),
    Example("AndroidView", "Use AndroidView to inflate Views in Compose", AndroidViewActivity::class.java),
    Example("AndroidViewBinding", "Use AndroidViewBinding to inflate viewbinding layouts in Compose", ViewBindingActivity::class.java),
)