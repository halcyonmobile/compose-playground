package com.halcyonmobile.viewinterop.example.theming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.android.material.composethemeadapter.MdcTheme

/**
 * The MDC Compose adapter is useful when migrating to Compose so the your Compose code will use the theme defined in XML.
 * See in this example how the styles in [MaterialTheme] match the theme set to [ThemingActivity].
 * For this to work you need to wrap your compose code with [MdcTheme].
 *
 * Note that there are some limitations see docs: https://github.com/material-components/material-components-android-compose-theme-adapter#limitations
 */
class ThemingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                MdcTheme { // The magic happens here
                    val insetPadding = rememberInsetsPaddingValues(
                        LocalWindowInsets.current.systemBars,
                        additionalStart = 16.dp,
                        additionalTop = 16.dp,
                        additionalBottom = 16.dp,
                        additionalEnd = 16.dp
                    )
                    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(4.dp), contentPadding = insetPadding) {
                        item { ThemeColors() }
                        item { Text(text = "H 1", style = MaterialTheme.typography.h1) }
                        item { Text(text = "H 2", style = MaterialTheme.typography.h2) }
                        item { Text(text = "H 3", style = MaterialTheme.typography.h3) }
                        item { Text(text = "Headline 4", style = MaterialTheme.typography.h4) }
                        item { Text(text = "Headline 5", style = MaterialTheme.typography.h5) }
                        item { Text(text = "Headline 6", style = MaterialTheme.typography.h6) }
                        item { Text(text = "Subtitle 1", style = MaterialTheme.typography.subtitle1) }
                        item { Text(text = "Subtitle 2", style = MaterialTheme.typography.subtitle2) }
                        item { Text(text = "Body 1", style = MaterialTheme.typography.body1) }
                        item { Text(text = "Body 2", style = MaterialTheme.typography.body2) }
                        item { Text(text = "Caption", style = MaterialTheme.typography.caption) }
                        item { Text(text = "Overline", style = MaterialTheme.typography.overline) }
                        item {
                            Button(onClick = { }) {
                                Text(text = "Primary Button")
                            }
                        }
                        item {
                            Spacer(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(MaterialTheme.shapes.small)
                                    .background(MaterialTheme.colors.onBackground)
                            )
                        }
                        item {
                            Spacer(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(MaterialTheme.shapes.medium)
                                    .background(MaterialTheme.colors.onBackground)
                            )
                        }
                        item {
                            Spacer(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(MaterialTheme.shapes.large)
                                    .background(MaterialTheme.colors.onBackground)
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ThemeColors() {
        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            item {
                Spacer(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colors.primary)
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colors.primaryVariant)
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colors.secondary)
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .size(24.dp)
                        .background(MaterialTheme.colors.secondaryVariant)
                )
            }
        }
    }
}
