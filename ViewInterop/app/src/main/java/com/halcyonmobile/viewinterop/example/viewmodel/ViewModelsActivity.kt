package com.halcyonmobile.viewinterop.example.viewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.halcyonmobile.viewinterop.databinding.ActivityViewmodelsBinding

class ViewModelsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityViewmodelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            Column(Modifier.padding(8.dp)) {
                Text(text = "Activity: ViewModel is scoped to the Activity")
                ComposeWithViewModels()
            }
        }
        binding.pager.adapter = PagerAdapter(this)
        binding.composePager.setContent {
            Box(
                Modifier
                    .padding(16.dp)
                    .background(Color.LightGray)) {
                ComposeNavigation()
            }
        }
    }
}