package com.halcyonmobile.viewinterop.example.abstractcomposeview

import android.content.Context
import android.util.AttributeSet
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.AbstractComposeView

class FancyButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

    var text by mutableStateOf("")
    var onClick by mutableStateOf({})

    @Composable
    override fun Content() {
        FancyButton(text, onClick)
    }
}

@Composable
fun FancyButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}