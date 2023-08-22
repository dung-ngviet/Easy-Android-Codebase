package com.dux.ng.easybase.ui.support

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Corner(
    val small: Dp = 8.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
)

val LocalCorner = compositionLocalOf { Corner() }

val MaterialTheme.corner: Corner
    @Composable
    @ReadOnlyComposable
    get() = LocalCorner.current