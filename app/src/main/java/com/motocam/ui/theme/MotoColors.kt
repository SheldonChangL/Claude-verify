package com.motocam.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

object MotoColors {
    val Background = Color(0xFF06080F)
    val Foreground = Color(0xFFFFFFFF)
    val Surface = Color(0xFF1A1F2E)
    val Accent = Color(0xFFAACC03)
    val OnAccent = Color(0xFF000000)
    val Success = Color(0xFF30D158)
    val Warning = Color(0xFFFF9F0A)
    val Danger = Color(0xFFFF4444)
    val Muted = Color(0xFFAAAAAA)
    val Outline = Color(0x14FFFFFF)
}

val darkScheme: ColorScheme = darkColorScheme(
    primary = MotoColors.Accent,
    onPrimary = MotoColors.OnAccent,
    secondary = MotoColors.Success,
    onSecondary = MotoColors.OnAccent,
    tertiary = MotoColors.Warning,
    onTertiary = MotoColors.OnAccent,
    background = MotoColors.Background,
    onBackground = MotoColors.Foreground,
    surface = MotoColors.Surface,
    onSurface = MotoColors.Foreground,
    onSurfaceVariant = MotoColors.Muted,
    error = MotoColors.Danger,
    onError = MotoColors.Foreground,
    outline = MotoColors.Outline,
)
