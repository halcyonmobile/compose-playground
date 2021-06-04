package com.halcyonmobile.composecomponents.component.animated_switch

import android.os.Build
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.halcyonmobile.composecomponents.R

val backgroundLightColor = Color(0xffebecf2)
val backgroundDarkColor = Color(0xff393a42)
val green = Color(0xff68ccb1)
val red = Color(0xffed6457)
val switchSize = DpOffset(200.dp, 400.dp)
val thumbSize = 200.dp

/**
 * Design reference https://dribbble.com/shots/11211853-ON-OFF-Switch
 */
@Preview
@Composable
fun AnimatedSwitch() {
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateValue(initialValue = 0.dp,
        targetValue = switchSize.y - thumbSize,
        typeConverter = Dp.VectorConverter,
        animationSpec = getAnimationSpec())
    val lightColor by infiniteTransition.animateColor(initialValue = green,
        targetValue = red,
        animationSpec = getAnimationSpec())
    val backgroundColor by infiniteTransition.animateColor(initialValue = backgroundLightColor,
        targetValue = backgroundDarkColor,
        animationSpec = getAnimationSpec())
    val onColor by infiniteTransition.animateColor(initialValue = green,
        targetValue = Color(0xff1a1b20),
        animationSpec = getAnimationSpec())
    val offColor by infiniteTransition.animateColor(initialValue = Color(0xffbdbccb),
        targetValue = red,
        animationSpec = getAnimationSpec())
    val bloomOnColor by infiniteTransition.animateColor(initialValue = green,
        targetValue = Color(0x00ffffff),
        animationSpec = getAnimationSpec())
    val bloomOffColor by infiniteTransition.animateColor(initialValue = Color(0x00ffffff),
        targetValue = red,
        animationSpec = getAnimationSpec())

    Box(Modifier
        .fillMaxSize()
        .background(backgroundColor), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BloomText(text = "ON", onColor, bloomOnColor)
            Switch(offset, lightColor, backgroundColor)
            BloomText(text = "OFF", offColor, bloomOffColor)
        }
    }
}

@Composable
private fun Switch(offsetY: Dp, color: Color, backgroundColor: Color) {
    Box {
        Box(Modifier
            .requiredSize(switchSize.x, switchSize.y)
            .clip(CircleShape)
            .drawInnerShadow()
            .clip(CircleShape)
            .background(backgroundColor)) {
        }
        Thumb(modifier = Modifier.offset(y = offsetY), color, backgroundColor)
    }
}

@Composable
private fun Thumb(modifier: Modifier = Modifier, color: Color, backgroundColor: Color) {
    Box(modifier) {
        Box(Modifier
            .size(thumbSize)
            .padding(16.dp)
            .drawColoredShadow(Color.DarkGray, offsetX = 8.dp, offsetY = 8.dp, borderRadius = thumbSize / 2, alpha = 0.4f)
            .drawColoredShadow(Color.LightGray, offsetX = (-8).dp, offsetY = (-8).dp, borderRadius = thumbSize / 2, alpha = 0.1f)
            .clip(CircleShape)
            .background(backgroundColor),
            contentAlignment = Alignment.Center) {
            Light(color)
        }
    }
}

@Composable
fun Light(color: Color) {
    Box(Modifier
        .size(40.dp)
        .drawColoredShadow(color, borderRadius = 20.dp, alpha = 0.8f)
        .clip(CircleShape)
        .background(color)) {
        Box(Modifier
            .size(40.dp)
            .clip(CircleShape)
            .drawInnerShadow(shadowRadius = 10.dp)) {

        }
    }
}

@Composable
fun BloomText(text: String, textColor: Color, bloomColor: Color) {
    Text(text,
        modifier = Modifier.padding(32.dp),
        style = TextStyle(color = textColor,
            letterSpacing = 1.sp,
            fontFamily = FontFamily(
                fonts = listOf(
                    Font(resId = R.font.montserrat_black,
                        weight = FontWeight.Bold,
                        style = FontStyle.Normal))),
            fontSize = 20.sp,
            shadow = Shadow(bloomColor, Offset(2f, 2f), 40f)))
}

@Composable
private fun <T> getAnimationSpec(): InfiniteRepeatableSpec<T> =
    infiniteRepeatable(tween(1500, easing = FastOutSlowInEasing), RepeatMode.Reverse)

fun Modifier.drawInnerShadow(
    alpha: Float = 0.2f,
    shadowRadius: Dp = 30.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
) = this.drawWithContent {
    drawContent()
    this.drawIntoCanvas {
        it.drawPath(Path().apply {
            this.addArc(Rect(0f, 0f, size.width, size.width),
                -70f, -110f)
            this.lineTo(0f, size.height)
            this.lineTo(-100f, size.height)
            this.lineTo(-100f, 0f)
        }, getPaint(Color.Black, shadowRadius, alpha, offsetX, offsetY))
        it.drawPath(Path().apply {
            this.moveTo(size.width + 100f, size.height)
            this.lineTo(size.width + 100f, 0f)
            this.lineTo(size.width, 0f)
            this.lineTo(size.width, size.height)
            this.arcTo(Rect(0f, size.height - size.width, size.width, size.height),
                0f, 120f, true)
            this.lineTo(size.width / 2, size.height)
        }, getPaint(Color.LightGray, shadowRadius, alpha, offsetX, offsetY))
    }
}

fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
) = this.drawBehind {
    this.drawIntoCanvas {
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            getPaint(color, shadowRadius, alpha, offsetX, offsetY)
        )
    }
}

fun DrawScope.getPaint(color: Color, shadowRadius: Dp, alpha: Float, offsetX: Dp, offsetY: Dp) = Paint().apply {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.asFrameworkPaint().apply {
            this.color = android.graphics.Color.TRANSPARENT
            setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
            )
        }
    }
}