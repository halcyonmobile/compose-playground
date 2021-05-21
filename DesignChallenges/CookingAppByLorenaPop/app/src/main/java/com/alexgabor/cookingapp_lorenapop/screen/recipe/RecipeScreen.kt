package com.alexgabor.cookingapp_lorenapop.screen.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexgabor.cookingapp_lorenapop.R
import com.alexgabor.cookingapp_lorenapop.catalog.Chip
import com.alexgabor.cookingapp_lorenapop.catalog.CircleButton
import com.alexgabor.cookingapp_lorenapop.catalog.SwipeButton
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipeScreen(navController: NavHostController) {
    val minOffset = 0f
    val maxOffset = with(LocalDensity.current) { (LocalConfiguration.current.screenWidthDp.dp - 24.dp).toPx() }
    var currentOffset by remember { mutableStateOf(maxOffset) }
    val listState = rememberLazyListState()

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {

            fun scrollBy(available: Float): Float {
                if (available > 0 && (listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0)) return 0f
                val availableEnd = (currentOffset + available)
                currentOffset = availableEnd.coerceIn(minOffset, maxOffset)
                return (available - (availableEnd - currentOffset))
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset(x = 0f, y = scrollBy(available.y))
            }
        }
    }
    Box(Modifier
        .fillMaxSize()
        .nestedScroll(nestedScrollConnection)) {
        Image(painter = painterResource(R.drawable.cookies), contentDescription = "Recipe Banner Imager", contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f))
        Toolbar(Modifier.statusBarsPadding())
        LazyColumn(Modifier
            .padding(top = with(LocalDensity.current) { currentOffset.toDp() })
            .fillMaxSize()
            .clip(AppTheme.shapes.bottomSheet)
            .background(AppTheme.colors.surface),
            state = listState,
            contentPadding = PaddingValues(AppTheme.dimens.screenPadding)) {
            item {
                Row {
                    Box(Modifier.weight(1f)) {
                        Text(text = "Chocolate Chip Cookies", style = AppTheme.typography.titleTall, modifier = Modifier)
                    }
                    Image(painterResource(R.drawable.ic_heart),
                        contentDescription = "Favourites",
                        Modifier.padding(start = AppTheme.dimens.iconSpacing, end = AppTheme.dimens.iconSpacing))
                }
            }
            item {
                Row(Modifier.padding(top = 32.dp)) {
                    Box(Modifier.weight(1f)) {
                        Text(text = "Rating")
                    }
                    Text(text = "Review", style = AppTheme.typography.flatButton)
                    Image(painter = painterResource(id = R.drawable.ic_plus),
                        contentDescription = "Add Review",
                        colorFilter = ColorFilter.tint(AppTheme.colors.accent),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(12.dp)
                            .align(CenterVertically))
                }
            }
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Chip(painter = painterResource(id = R.drawable.ic_clock), text = "1.5 h")
                    Chip(painter = painterResource(id = R.drawable.ic_fire), text = "365 kcal")
                    Chip(painter = painterResource(id = R.drawable.ic_eat), text = "12 pc")
                }
            }
            items(50) {
                Text(text = "To find the absolute best, we tested a ton of variables — various different kinds of sugar, fat, and flour — then put it all together to create the ultimate chocolate chip cookie.",
                    style = AppTheme.typography.bodyNormalOnSurface,
                    modifier = Modifier.padding(top = 32.dp))
            }
            item {
                Text(text = "To find the absolute best, we tested a ton of variables — various different kinds of sugar, fat, and flour — then put it all together to create the ultimate chocolate chip cookie.",
                    style = AppTheme.typography.bodyNormalOnSurface,
                    modifier = Modifier.padding(top = 32.dp))
            }
            item {
                Box(Modifier
                    .navigationBarsPadding()) {
                    Spacer(Modifier.size(AppTheme.dimens.buttonHeight))
                }
            }
        }
        SwipeButton(modifier = Modifier
            .fillMaxWidth()
            .align(BottomCenter)
            .padding(AppTheme.dimens.screenPadding)
            .navigationBarsPadding())
    }
}

@Composable
private fun Toolbar(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = AppTheme.dimens.screenPadding),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Box(Modifier.weight(1f)) {
            CircleButton {
                Image(painter = painterResource(id = R.drawable.ic_arrow_forward),
                    modifier = Modifier.rotate(-180f),
                    contentDescription = "Go back",
                    colorFilter = ColorFilter.tint(AppTheme.colors.textOnSurface))
            }
        }
        CircleButton {
            Image(painter = painterResource(id = R.drawable.ic_share),
                contentDescription = "Go back",
                colorFilter = ColorFilter.tint(AppTheme.colors.textOnSurface))
        }
        CircleButton {
            Image(painter = painterResource(id = R.drawable.ic_shopping_cart),
                contentDescription = "Go back",
                colorFilter = ColorFilter.tint(AppTheme.colors.textOnSurface))
        }
    }
}