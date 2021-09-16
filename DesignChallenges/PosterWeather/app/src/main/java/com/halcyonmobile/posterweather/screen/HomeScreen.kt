package com.halcyonmobile.posterweather.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.halcyonmobile.posterweather.R
import com.halcyonmobile.posterweather.catalog.Clouds
import com.halcyonmobile.posterweather.catalog.HorizontalDivider
import com.halcyonmobile.posterweather.catalog.Sunny
import com.halcyonmobile.posterweather.catalog.VerticalDivider
import com.halcyonmobile.posterweather.catalog.borderItem
import com.halcyonmobile.posterweather.theme.AppTheme

@Composable
fun HomeScreen() {
    LazyColumn(contentPadding = PaddingValues(AppTheme.dimens.screenPadding),
        modifier = Modifier
            .fillMaxWidth()) {
        item { Spacer(modifier = Modifier.statusBarsPadding()) }
        borderItem(top = true) { Toolbar() }
        borderItem { HorizontalDivider(width = AppTheme.dimens.borderWidth) }
        borderItem { DateAndLocation() }
        borderItem { HorizontalDivider(width = AppTheme.dimens.borderWidth) }
        borderItem { TodayWeather() }
        borderItem { HorizontalDivider(width = AppTheme.dimens.dividerWidth) }
        borderItem(bottom = true) { UpcomingDays() }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Composable
private fun DateAndLocation() {
    Row(Modifier.height(IntrinsicSize.Min)) {
        TodayDate()
        VerticalDivider(width = AppTheme.dimens.borderWidth)
        Location()
    }
}

@Composable
fun Toolbar() {
    Box(Modifier
        .fillMaxWidth()
        .padding(AppTheme.dimens.toolbarPadding)) {
        Image(painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu",
            colorFilter = ColorFilter.tint(AppTheme.colors.ink),
            alignment = Alignment.CenterStart)
        BasicText(text = "TODAY", style = AppTheme.typography.regular, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun TodayDate() {
    Box(Modifier.width(128.dp)) {
        Image(
            painter = painterResource(id = R.drawable.background), null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop,
        )
        BasicText(text = "May 4,\nTue", style = AppTheme.typography.boldInverse,
            modifier = Modifier.padding(vertical = AppTheme.dimens.contentPaddingVertical, horizontal = AppTheme.dimens.contentPaddingHorizontal))
    }
}

@Composable
fun Location() {
    Box(Modifier
        .fillMaxWidth()
        .padding(vertical = AppTheme.dimens.contentPaddingVertical, horizontal = AppTheme.dimens.contentPaddingHorizontal)) {
        BasicText(text = "Sydney,\nNSW", Modifier.fillMaxWidth(), style = AppTheme.typography.bold)
        Image(painter = painterResource(id = R.drawable.ic_forward_arrow),
            contentDescription = "Location",
            colorFilter = ColorFilter.tint(AppTheme.colors.ink),
            modifier = Modifier.align(Alignment.TopEnd))
    }
}

@Composable
fun TodayWeather() {
    Box {
        Sunny(Modifier
            .offset(x = 24.dp)
            .zIndex(1f)
            .size(176.dp)
            .align(Alignment.TopEnd))
        Column {
            ConstraintLayout(Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.contentPaddingHorizontal)) {
                val (big, small, description) = createRefs()
                BasicText(text = "25", style = AppTheme.typography.big,
                    modifier = Modifier.offset(x = (-8).dp).constrainAs(big) { top.linkTo(parent.top) })
                BasicText(text = "°C", style = AppTheme.typography.bold,
                    modifier = Modifier.constrainAs(small) { baseline.linkTo(big.baseline); start.linkTo(big.end) })
                BasicText(text = "Clear and sunny", style = AppTheme.typography.bold,
                    modifier = Modifier.constrainAs(description) { top.linkTo(big.bottom, (-24).dp); start.linkTo(parent.start) })
            }
            Row(Modifier.padding(start = AppTheme.dimens.contentPaddingHorizontal, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.contentPaddingHorizontal)) {
                LabelTemperature("LOW", 20)
                LabelTemperature("HIGH", 28)
            }
        }
    }
}

@Composable
fun LabelTemperature(label: String, temperature: Int) {
    Column {
        BasicText(text = label, style = AppTheme.typography.regular)
        BasicText(text = "$temperature°C", style = AppTheme.typography.bold)
    }
}

@Composable
fun UpcomingDays() {
    Column(Modifier.fillMaxWidth()) {
        Days.forEachIndexed { index, day ->
            UpcomingDay(day.day, day.low, day.high, day.sky)
            if (index != Days.size - 1) {
                HorizontalDivider(width = AppTheme.dimens.borderWidth)
            }
        }
    }
}

@Composable
fun UpcomingDay(day: String, low: Int, high: Int, sky: Sky) {
    Box(Modifier
        .fillMaxWidth()
        .padding(vertical = AppTheme.dimens.contentPaddingVertical, horizontal = AppTheme.dimens.contentPaddingHorizontal)) {
        BasicText(text = day, style = AppTheme.typography.bold, modifier = Modifier.align(Alignment.CenterStart))
        LowHigh(low, high, Modifier.align(Alignment.Center).padding(end = AppTheme.dimens.contentPaddingHorizontal))
        val skyModifier = Modifier
            .requiredSize(AppTheme.dimens.skyIconSize)
            .align(Alignment.CenterEnd)
        when (sky) {
            Sky.Sunny -> Sunny(skyModifier)
            Sky.Cloudy -> Clouds(skyModifier)
        }
    }
}

@Composable
fun LowHigh(low: Int, high: Int, modifier: Modifier = Modifier) {
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(AppTheme.dimens.temperatureSpacing)) {
        BasicText(text = "$low°", style = AppTheme.typography.regular)
        BasicText(text = "/", style = AppTheme.typography.regular)
        BasicText(text = "$high°", style = AppTheme.typography.regular)
    }
}
