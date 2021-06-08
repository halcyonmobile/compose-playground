package com.halcyonmobile.posterweather.screen

data class Day(
    val day: String,
    val low: Int,
    val high: Int,
    val sky: Sky,
)

sealed class Sky {
    object Sunny : Sky()
    object Cloudy : Sky()
}

val Days = listOf(
    Day("Wed", 24, 28, Sky.Sunny),
    Day("Thu", 20, 24, Sky.Cloudy),
    Day("Fri", 22, 24, Sky.Cloudy),
)