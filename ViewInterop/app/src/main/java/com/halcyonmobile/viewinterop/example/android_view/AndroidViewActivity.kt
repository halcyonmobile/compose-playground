package com.halcyonmobile.viewinterop.example.android_view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart

class AndroidViewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var lineData by remember { mutableStateOf(getLineData(getRandomChartEntries())) }

            Column {
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