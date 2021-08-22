package com.halcyonmobile.viewinterop

import androidx.activity.ComponentActivity
import com.halcyonmobile.viewinterop.example.abstractcomposeview.AbstractComposeViewActivity
import com.halcyonmobile.viewinterop.example.android_view.AndroidViewActivity
import com.halcyonmobile.viewinterop.example.compose_view.ComposeViewActivity
import com.halcyonmobile.viewinterop.example.dynamic_compose_view.DynamicComposeViewActivity
import com.halcyonmobile.viewinterop.example.theming.ThemingActivity
import com.halcyonmobile.viewinterop.example.viewbinding.ViewBindingActivity
import com.halcyonmobile.viewinterop.example.viewmodel.ViewModelsActivity

data class Example(val title: String, val subtitle: String, val activity: Class<out ComponentActivity>)

val Examples = listOf(
    Example("ComposeView", "Add compose in XML", ComposeViewActivity::class.java),
    Example("ComposeView added dynamically", "Add compose dynamically in XML", DynamicComposeViewActivity::class.java),
    Example("AndroidView", "Use AndroidView to inflate Views in Compose", AndroidViewActivity::class.java),
    Example("AndroidViewBinding", "Use AndroidViewBinding to inflate viewbinding layouts in Compose", ViewBindingActivity::class.java),
    Example("AbstractComposeView", "Wrap Composable function in a view which extends AbstractComposeView to use them in Android views", AbstractComposeViewActivity::class.java),
    Example("ViewModels in Compose", "ViewModels can be scoped to Activity, Fragment or Destination", ViewModelsActivity::class.java),
    Example("MDC compose adapter", "Use MDC compose adapter to use in Compose the material theme from XML", ThemingActivity::class.java),
)