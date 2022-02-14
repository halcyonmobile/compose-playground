package com.halcyonmobile.composecomponents

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.halcyonmobile.composecomponents.start.StartScreen
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun ComponentsApp(backDispatcher: OnBackPressedDispatcher) {
    MaterialTheme {
        ProvideWindowInsets {
            AppContent(backDispatcher)
        }
    }
}

@Composable
fun AppContent(backDispatcher: OnBackPressedDispatcher) {
    val componentIndex = rememberSaveable { mutableStateOf(-1) }
    val component = remember { mutableStateOf(if (componentIndex.value == -1) null else components[componentIndex.value]) }

    val backCallback = object : OnBackPressedCallback(component.value != null) {
        override fun handleOnBackPressed() {
            component.value = null
            isEnabled = false
        }
    }
    backDispatcher.addCallback(backCallback)

    val componentValue = component.value
    if (componentValue != null) {
        componentValue.content()
    } else {
        StartScreen(components) { index, newComponent ->
            componentIndex.value = index
            component.value = newComponent
        }
    }

    DisposableEffect(Unit) {
        onDispose { backCallback.remove() }
    }
}