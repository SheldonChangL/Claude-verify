package com.motocam.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.RippleDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@OptIn(ExperimentalMaterial3Api::class)
private val MotoRippleConfiguration = RippleConfiguration(
    color = MotoColors.Accent,
    rippleAlpha = RippleDefaults.RippleAlpha,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotoTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = MotoColors.darkScheme, typography = MotoTypography) {
        CompositionLocalProvider(
            LocalRippleConfiguration provides MotoRippleConfiguration,
            content = content,
        )
    }
}
