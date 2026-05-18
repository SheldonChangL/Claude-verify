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
    val WarningSoft = Color(0x2EFF9F0A)
    val DangerSoft = Color(0x2EFF4444)
    val IconBubbleWarning = Color(0x2EFF9F0A)
    val IconBubbleDanger = Color(0x38FF4444)

    val darkScheme: ColorScheme = darkColorScheme(
        primary = Accent,
        onPrimary = OnAccent,
        secondary = Success,
        onSecondary = OnAccent,
        tertiary = Warning,
        onTertiary = OnAccent,
        background = Background,
        onBackground = Foreground,
        surface = Surface,
        onSurface = Foreground,
        onSurfaceVariant = Muted,
        error = Danger,
        onError = Foreground,
        outline = Outline,
    )
}