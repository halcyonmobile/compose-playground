package com.halcyonmobile.viewinterop.example.compose_view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.halcyonmobile.viewinterop.InteropApp
import com.halcyonmobile.viewinterop.databinding.ActivityComposeViewBinding

class ComposeViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityComposeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            ComposeViewContent()
        }
    }
}

@Composable
fun ComposeViewContent() {
    Column(
        Modifier
            .fillMaxSize()
            .border(4.dp, Color.LightGray)
    ) {
        InteropApp()
    }
}