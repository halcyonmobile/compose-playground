package com.halcyonmobile.viewinterop.example.viewbinding

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.github.mikephil.charting.utils.Utils
import com.halcyonmobile.viewinterop.databinding.ActivityViewBindingBinding
import com.halcyonmobile.viewinterop.example.android_view.getLineData
import com.halcyonmobile.viewinterop.example.android_view.getRandomChartEntries

/**
 * Use AndroidViewBinding to inflate binding in Compose.
 * See [AndroidViewBinding] doc for details on how to use it.
 */
class ViewBindingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Utils.convertDpToPixel(LocalDensity.current.density) // for MPChart

            Column {
                Description(Modifier.padding(16.dp))

                var lineData by remember { mutableStateOf(getLineData(getRandomChartEntries())) }
                AndroidViewBinding(
                    modifier = Modifier.padding(16.dp),
                    factory = { inflater, parent, attachToParent ->
                        ActivityViewBindingBinding.inflate(inflater, parent, attachToParent).apply {
                            shuffle.setOnClickListener {
                                lineData = getLineData(getRandomChartEntries())
                            }
                        }
                    },
                    update = {
                        if (chart.data != lineData) {
                            chart.data = lineData
                            chart.invalidate() // MPChart requires it. It is not needed for other views.
                        }
                    })
            }
        }
    }
}

@Composable
private fun Description(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        val uriHandler = LocalUriHandler.current
        Text(text = "You can use AndroidViewBinding to inflate viewbinding layouts in Compose", Modifier.padding(bottom = 8.dp))
        OutlinedButton(
            onClick = { uriHandler.openUri("https://developer.android.com/jetpack/compose/interop/interop-apis#views-in-compose") }) {
            Text(text = "See docs")
        }
    }
}