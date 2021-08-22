package com.halcyonmobile.viewinterop.example.android_view

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlin.random.Random

fun getRandomChartEntries() = (0..5).mapIndexed { index, _ ->
    Entry(index.toFloat(), Random.nextFloat()*10f)
}

fun getLineData(data: List<Entry>) = LineData(
    LineDataSet(data, "")
)
