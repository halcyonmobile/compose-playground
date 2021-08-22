package com.halcyonmobile.viewinterop.example.abstractcomposeview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.halcyonmobile.viewinterop.databinding.ActivityAbstractComposeViewBinding

/**
 * Extend [AbstractComposeView] to wrap your Composables and be able to use them as Views. See [FancyButton]
 * This is useful when migrating to Compose so that you have the equivalent in View of your Compose component.
 * Alternatively you could convert your existing custom view to Compose without changing how it is used.
 */
class AbstractComposeViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAbstractComposeViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var times = 0
        binding.fancyButton.text = "Fancy"
        binding.fancyButton.onClick = {
            binding.times.text = "Clicked ${++times} times"
        }
    }
}
