package com.halcyonmobile.viewinterop.example.android_view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.utils.Utils

/**
 * Use AndroidView to add Views in Compose.
 * See [AndroidView] doc for details on how to use it.
 */
class AndroidViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Utils.convertDpToPixel(LocalDensity.current.density) // for MPChart

            var lineData by remember { mutableStateOf(getLineData(getRandomChartEntries())) }

            Column {
                Description(Modifier.padding(16.dp))
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(16.dp),
                    factory = { context ->
                        LineChart(context).apply {
                            this.data = lineData
                        }
                    },
                    update = { view ->
                        if (view.data != lineData) {
                            view.data = lineData
                            view.invalidate() // MPChart requires it. It is not needed for other views.
                        }
                    }
                )

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { lineData = getLineData(getRandomChartEntries()) }) {
                        Text(text = "Shuffle")
                    }
                }
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
        Text(text = "You can use AndroidView to inflate Views in Compose", Modifier.padding(bottom = 8.dp))
        OutlinedButton(
            onClick = { uriHandler.openUri("https://developer.android.com/jetpack/compose/interop/interop-apis#views-in-compose") }) {
            Text(text = "See docs")
        }
    }
}