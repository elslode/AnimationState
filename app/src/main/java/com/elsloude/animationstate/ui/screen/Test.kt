package com.elsloude.animationstate.ui.screen

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Test() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(
                state = rememberScrollState(),
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var isIncreased by remember {
            mutableStateOf(true)
        }

        val infiniteTransition = rememberInfiniteTransition()
        val size by infiniteTransition.animateFloat(
            initialValue = 200f,
            targetValue = 100f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000),
                repeatMode = RepeatMode.Reverse
            )
        )

        val rotation by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(1000, easing = LinearEasing)
            )
        )

        Button(
            onClick = { isIncreased = !isIncreased },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Animate size",
            )
        }
        AnimatedContainer(
            text = "Size",
            size = size.dp
        )

        var isRect by remember {
            mutableStateOf(true)
        }

        val radius by animateIntAsState(
            targetValue = if (isRect) 4 else 50
        )

        Button(
            onClick = { isRect = !isRect },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Animate shape"
            )
        }
        AnimatedContainer(
            text = "shape",
            radiusPercent = radius,
            rotation = rotation
        )

        var isBordered by remember {
            mutableStateOf(true)
        }
        val border by animateDpAsState(targetValue = if (isBordered) 0.dp else 10.dp)

        Button(
            onClick = { isBordered = !isBordered },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Animate border"
            )
        }
        AnimatedContainer(
            text = "border",
            borderWidth = border
        )

        var hasNewColor by remember {
            mutableStateOf(false)
        }
        val color by animateColorAsState(targetValue = if (hasNewColor) Color.Magenta else Color.Blue)

        Button(
            onClick = { hasNewColor = !hasNewColor },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Animate color"
            )
        }
        AnimatedContainer(
            text = "Color",
            background = color
        )

        var isVisible by remember {
            mutableStateOf(true)
        }
        val alpha by animateFloatAsState (targetValue = if (isVisible) 1f else 0f)

        Button(
            onClick = { isVisible = !isVisible },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Change Visibility"
            )
        }
        AnimatedContainer(
            text = "Visibility",
            alpha = alpha
        )
    }
}
@Composable
private fun AnimatedContainer(
    text: String,
    size: Dp = 200.dp,
    radiusPercent: Int = 4,
    borderWidth: Dp = 0.dp,
    background: Color = Color.Blue,
    alpha: Float = 1f,
    rotation: Float = 0f
) {
    Box(
        modifier = Modifier
            .rotate(rotation)
            .alpha(alpha)
            .clip(RoundedCornerShape(radiusPercent))
            .border(borderWidth, color = background)
            .background(background)
            .size(size),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}