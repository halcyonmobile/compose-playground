package com.halcyonmobile.viewinterop.example.abstractcomposeview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.halcyonmobile.viewinterop.databinding.ActivityAbstractComposeViewBinding

class AbstractComposeViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAbstractComposeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fancyButton.text = "Fancy"
        binding.fancyButton.onClick = {
            Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show()
        }
    }
}
