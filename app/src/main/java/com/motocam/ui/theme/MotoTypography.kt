package com.motocam.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object MotoTypography {
    private val family: FontFamily = MotoFonts.primary

    val values: Typography = Typography(
        displayLarge = TextStyle(
            fontFamily = family,
            fontSize = 96.sp,
            fontWeight = FontWeight.Black,
            lineHeight = 96.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = family,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        ),
        titleLarge = TextStyle(
            fontFamily = family,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
        ),
        titleMedium = TextStyle(
            fontFamily = family,
            fontSize = 17.sp,
            fontWeight = FontWeight.Black,
        ),
        titleSmall = TextStyle(
            fontFamily = family,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodyLarge = TextStyle(
            fontFamily = family,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
        ),
        bodyMedium = TextStyle(
            fontFamily = family,
            fontSize = 13.sp,
            fontWeight = FontWeight.Normal,
        ),
        bodySmall = TextStyle(
            fontFamily = family,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
        ),
        labelLarge = TextStyle(
            fontFamily = family,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
        ),
        labelMedium = TextStyle(
            fontFamily = family,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
        ),
        labelSmall = TextStyle(
            fontFamily = family,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
        ),
    )
}
