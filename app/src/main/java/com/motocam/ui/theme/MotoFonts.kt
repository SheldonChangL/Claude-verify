package com.motocam.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.motocam.R

object MotoFonts {
    val primary: FontFamily = FontFamily(
        Font(R.font.noto_sans_tc_regular, FontWeight.Normal, FontStyle.Normal),
        Font(R.font.noto_sans_tc_bold, FontWeight.Bold, FontStyle.Normal),
    )
}
