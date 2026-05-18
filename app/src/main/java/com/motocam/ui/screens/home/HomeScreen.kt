package com.motocam.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import com.motocam.R
import com.motocam.ui.Strings
import com.motocam.ui.theme.MotoColors
import com.motocam.ui.theme.MotoTheme

@Composable
fun HomeScreen(state: HomeUiState = HomeUiState.mock()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MotoColors.Background)
            .verticalScroll(rememberScrollState()),
    ) {
        StatusBar(clock = state.clock, recording = state.recording)
        SpeedHero(speed = state.speedKmh, unit = state.speedUnit)
        Spacer(Modifier.height(8.dp))
        if (state.banner.visible) {
            AlertBannerCard(state.banner)
            Spacer(Modifier.height(8.dp))
        }
        CameraViewportCard(state.camera)
        Spacer(Modifier.height(12.dp))
        ImageQualityRow(state.imageQuality)
        Spacer(Modifier.height(12.dp))
        ReminderStrip(state.reminderText)
        Spacer(Modifier.height(12.dp))
        ShutterButton()
        Spacer(Modifier.height(12.dp))
        AlertChipRow(state.alertChips)
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun StatusBar(clock: String, recording: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = clock,
            color = MotoColors.Foreground,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 50))
                .background(Color.Black)
                .padding(horizontal = 10.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(if (recording) MotoColors.Danger else MotoColors.Muted),
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = Strings.REC,
                    color = MotoColors.Foreground,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun SpeedHero(speed: Int, unit: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = speed.toString(),
            color = MotoColors.Accent,
            fontSize = 96.sp,
            fontWeight = FontWeight.Black,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = unit,
            color = MotoColors.Accent,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 14.dp),
        )
    }
}

@Composable
private fun CameraViewportCard(camera: CameraOverlayState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFF0A0D14))
            .border(1.dp, MotoColors.Outline, RoundedCornerShape(12.dp))
            .aspectRatio(16f / 9f),
    ) {
        Image(
            painter = painterResource(id = R.drawable.user_moto),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(56.dp),
            contentScale = ContentScale.Fit,
        )
        Image(
            painter = painterResource(id = R.drawable.object_moto),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 60.dp, bottom = 40.dp)
                .size(36.dp),
            contentScale = ContentScale.Fit,
        )
        Image(
            painter = painterResource(id = R.drawable.object_car),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 18.dp)
                .size(42.dp),
            contentScale = ContentScale.Fit,
        )

        // REC pill top-right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(
                    if (camera.recording) MotoColors.Danger.copy(alpha = 0.85f)
                    else Color(0xCC787878),
                )
                .padding(horizontal = 8.dp, vertical = 3.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(MotoColors.Foreground),
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = camera.recBadgeText,
                    color = MotoColors.Foreground,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        // Timestamp bottom-left
        Text(
            text = camera.timestamp,
            color = MotoColors.Warning,
            fontSize = 11.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 8.dp),
        )

        // Fullscreen button bottom-right
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .size(28.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(Color(0x8C000000))
                .border(1.dp, Color(0x33FFFFFF), RoundedCornerShape(7.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "⛶",
                color = MotoColors.Foreground,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun AlertBannerCard(banner: AlertBannerState) {
    val (accent, bg) = when (banner.kind) {
        AlertBannerKind.BSD -> MotoColors.Warning to Color(0xE5281C05)
        AlertBannerKind.RCW -> MotoColors.Danger to Color(0xE5280505)
        AlertBannerKind.FALL -> Color(0xFFFF5864) to Color(0xEB2D050C)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(bg)
            .border(1.dp, accent.copy(alpha = 0.45f), RoundedCornerShape(14.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(accent.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "!", color = accent, fontSize = 17.sp, fontWeight = FontWeight.Black)
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = banner.title,
                color = accent,
                fontSize = 17.sp,
                fontWeight = FontWeight.Black,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = banner.subtitle,
                color = MotoColors.Foreground.copy(alpha = 0.55f),
                fontSize = 13.sp,
            )
        }
        Text(
            text = banner.time,
            color = MotoColors.Foreground.copy(alpha = 0.3f),
            fontSize = 13.sp,
        )
    }
}

@Composable
private fun ImageQualityRow(iq: ImageQualityState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MotoColors.Surface)
            .border(1.dp, MotoColors.Outline, RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "▸",
            color = MotoColors.Muted,
            fontSize = 13.sp,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = iq.title,
            color = MotoColors.Muted,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "${Strings.IQ_BRIGHTNESS} / ${Strings.IQ_CONTRAST} / ${Strings.IQ_SATURATION} / ${Strings.IQ_SHARPNESS}",
            color = MotoColors.Muted.copy(alpha = 0.6f),
            fontSize = 11.sp,
        )
    }
}

@Composable
private fun ReminderStrip(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xFF1E1E1E))
            .border(1.dp, MotoColors.Outline, RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(MotoColors.Success),
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            color = MotoColors.Foreground,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun ShutterButton() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .border(1.dp, MotoColors.Outline, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_btn_shutter),
                contentDescription = Strings.SHUTTER_DESC,
                tint = MotoColors.Foreground,
                modifier = Modifier.size(40.dp),
            )
        }
    }
}

@Composable
private fun AlertChipRow(chips: List<AlertChip>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        chips.forEach { chip ->
            AlertChipView(chip, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun AlertChipView(chip: AlertChip, modifier: Modifier = Modifier) {
    val color = when (chip.kind) {
        AlertChipKind.BSD_LEFT, AlertChipKind.BSD_RIGHT -> MotoColors.Warning
        AlertChipKind.RCW -> MotoColors.Danger
        AlertChipKind.TILT -> Color(0xFFFF5864)
        AlertChipKind.CLEAR -> MotoColors.Muted
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(percent = 50))
            .background(color.copy(alpha = 0.14f))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(percent = 50))
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = chip.label,
            color = color,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF06080F, widthDp = 390, heightDp = 844)
@Composable
private fun HomeScreenPreview() {
    MotoTheme {
        HomeScreen()
    }
}
