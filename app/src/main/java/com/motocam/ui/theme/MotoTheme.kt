package com.motocam.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MotoTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = MotoColors.darkScheme, content = content)
}
