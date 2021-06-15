package com.halcyonmobile.viewinterop

import androidx.activity.ComponentActivity

data class Example(val title: String, val subtitle: String, val activity: Class<out ComponentActivity>)

val Examples = listOf(
    Example("ComposeView", "Add compose in XML", MainActivity::class.java),
)