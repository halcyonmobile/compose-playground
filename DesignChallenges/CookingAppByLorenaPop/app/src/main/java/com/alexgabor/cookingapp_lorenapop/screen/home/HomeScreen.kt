package com.alexgabor.cookingapp_lorenapop.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alexgabor.cookingapp_lorenapop.R
import com.alexgabor.cookingapp_lorenapop.catalog.BannerButton
import com.alexgabor.cookingapp_lorenapop.catalog.Carousel
import com.alexgabor.cookingapp_lorenapop.catalog.Title
import com.alexgabor.cookingapp_lorenapop.screen.Destination
import com.alexgabor.cookingapp_lorenapop.screen.home.model.categories
import com.alexgabor.cookingapp_lorenapop.theme.AppTheme
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(Modifier.fillMaxSize()) {
        Toolbar()
        LazyColumn {
            item {
                Title("Hello, Jenn!", modifier = Modifier.padding(start = AppTheme.dimens.screenPadding, end = AppTheme.dimens.screenPadding, bottom = 24.dp))
            }
            item {
                Text(AnnotatedString("Trending", AppTheme.typography.bodyBoldOnSurface.toSpanStyle()) +
                        AnnotatedString(" Chocolate Chip Cookies", AppTheme.typography.bodyNormalOnSurface.toSpanStyle()),
                    modifier = Modifier.padding(start = AppTheme.dimens.screenPadding, end = AppTheme.dimens.screenPadding, bottom = 8.dp))
            }
            item {
                Box(Modifier.padding(start = AppTheme.dimens.screenPadding, end = AppTheme.dimens.screenPadding)) {
                    Image(painterResource(R.drawable.cookies), contentDescription = "Trending Recipe", contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = AppTheme.dimens.buttonHeight / 2 + 32.dp)
                            .height(AppTheme.dimens.bannerHeight)
                            .clip(AppTheme.shapes.standard))
                    BannerButton(Modifier
                        .fillMaxWidth()
                        .padding(start = 32.dp, end = 32.dp, bottom = 32.dp)
                        .height(AppTheme.dimens.buttonHeight)
                        .align(Alignment.BottomCenter),
                        onClick = { navController.navigate(Destination.Recipe) },
                        content = { offset ->
                            Text("Try this recipe", style = AppTheme.typography.button,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(start = AppTheme.dimens.bannerButtonPadding, end = offset)
                            )
                        }
                    )
                }
            }
            item {
                Carousel(text = { Text(text = "Top Categories", style = AppTheme.typography.subtitle, modifier = Modifier.padding(start = AppTheme.dimens.screenPadding)) },
                    indicatorModifier = Modifier.padding(end = AppTheme.dimens.screenPadding),
                    content = {
                        itemsIndexed(categories) { index, item ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                                .padding(top = 32.dp)
                                .padding(start = if (index == 0) AppTheme.dimens.screenPadding else AppTheme.dimens.carouselItemSpacing / 2,
                                    end = if (index == categories.size - 1) AppTheme.dimens.screenPadding else AppTheme.dimens.carouselItemSpacing / 2)
                                .size(AppTheme.dimens.categoryItemWidth, AppTheme.dimens.categoryItemHeight)
                                .clip(AppTheme.shapes.standard)
                                .background(AppTheme.colors.surfaceVariant)
                                .padding(top = 32.dp, bottom = 24.dp)) {
                                Image(painterResource(item.res), item.name, Modifier.size(AppTheme.dimens.categoryIconSize))
                                Text(item.name, style = AppTheme.typography.itemName, modifier = Modifier.padding(top = 24.dp))
                                Text(item.count, style = AppTheme.typography.itemCount, modifier = Modifier.padding(top = 8.dp))
                            }
                        }
                    })
            }
            item {
                Spacer(modifier = Modifier
                    .navigationBarsPadding()
                    .padding(bottom = AppTheme.dimens.screenPadding))
            }
        }
    }
}

@Composable
fun Toolbar() {
    Row(Modifier
        .fillMaxWidth()
        .statusBarsPadding()
        .padding(start = AppTheme.dimens.screenPadding, top = 24.dp, end = AppTheme.dimens.screenPadding, bottom = 24.dp)
        .height(AppTheme.dimens.toolbarHeight),
        verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier.weight(1f)) {
            Image(painterResource(R.drawable.profile), contentDescription = "Profile", contentScale = ContentScale.Crop, modifier = Modifier
                .clip(CircleShape)
                .size(AppTheme.dimens.screenPadding))
        }
        Image(painterResource(R.drawable.ic_search), contentDescription = "Search", Modifier.padding(start = AppTheme.dimens.iconSpacing, end = AppTheme.dimens.iconSpacing))
        Image(painterResource(R.drawable.ic_shopping_cart), contentDescription = "Shopping cart", Modifier.padding(start = AppTheme.dimens.iconSpacing))
    }
}



