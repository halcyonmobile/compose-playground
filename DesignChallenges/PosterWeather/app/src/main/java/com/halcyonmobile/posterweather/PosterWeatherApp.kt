package com.halcyonmobile.posterweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets
import com.halcyonmobile.posterweather.screen.HomeScreen
import com.halcyonmobile.posterweather.theme.AppTheme
import com.halcyonmobile.posterweather.theme.PosterTheme

@Composable
fun PosterWeatherApp() {
    ProvideWindowInsets {
        PosterTheme {
            Box(Modifier
                .fillMaxSize()
                .background(AppTheme.colors.paper)) {
                HomeScreen()
            }
        }
    }
}