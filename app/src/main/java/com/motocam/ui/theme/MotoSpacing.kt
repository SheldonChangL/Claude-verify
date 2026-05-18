package com.motocam.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val hairline: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val iconSm: Dp,
    val thumbWidth: Dp,
    val thumbHeight: Dp,
) {
    companion object {
        val Default: Spacing = fromTokens()

        fun fromTokens(): Spacing = Spacing(
            hairline = 1.dp,
            xs = 4.dp,
            sm = 8.dp,
            md = 12.dp,
            lg = 16.dp,
            xl = 20.dp,
            iconSm = 24.dp,
            thumbWidth = 88.dp,
            thumbHeight = 56.dp,
        )
    }
}

val LocalSpacing = staticCompositionLocalOf { Spacing.Default }
