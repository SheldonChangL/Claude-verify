package com.motocam.ui.screens.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
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
import com.motocam.R
import com.motocam.ui.Strings
import com.motocam.ui.theme.MotoTheme

@Composable
fun HomeScreen(state: HomeUiState = HomeUiState.mock()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = clock,
            color = scheme.onBackground,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 50))
                .background(scheme.background)
                .padding(horizontal = 10.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .clip(CircleShape)
                        .background(if (recording) scheme.error else scheme.onSurfaceVariant),
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = Strings.REC,
                    color = scheme.onBackground,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
private fun SpeedHero(speed: Int, unit: String) {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = speed.toString(),
            color = scheme.primary,
            fontSize = 96.sp,
            fontWeight = FontWeight.Black,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = unit,
            color = scheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 14.dp),
        )
    }
}

@Composable
private fun CameraViewportCard(camera: CameraOverlayState) {
    val scheme = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(scheme.background)
            .border(1.dp, scheme.outline, RoundedCornerShape(12.dp))
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

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(
                    if (camera.recording) scheme.error.copy(alpha = 0.85f)
                    else scheme.onSurfaceVariant.copy(alpha = 0.8f),
                )
                .padding(horizontal = 8.dp, vertical = 3.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(scheme.onBackground),
                )
                Spacer(Modifier.width(4.dp))
                Text(
                    text = camera.recBadgeText,
                    color = scheme.onBackground,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Text(
            text = camera.timestamp,
            color = scheme.tertiary,
            fontSize = 11.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 8.dp),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp)
                .size(28.dp)
                .clip(RoundedCornerShape(7.dp))
                .background(scheme.background.copy(alpha = 0.55f))
                .border(1.dp, scheme.outline, RoundedCornerShape(7.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = Strings.FULLSCREEN_GLYPH,
                color = scheme.onBackground,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun AlertBannerCard(banner: AlertBannerState) {
    val scheme = MaterialTheme.colorScheme
    val accent: Color = when (banner.kind) {
        AlertBannerKind.BSD -> scheme.tertiary
        AlertBannerKind.RCW -> scheme.error
        AlertBannerKind.FALL -> scheme.error
    }
    val bg = accent.copy(alpha = 0.12f)
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
                color = scheme.onBackground.copy(alpha = 0.55f),
                fontSize = 13.sp,
            )
        }
        Text(
            text = banner.time,
            color = scheme.onBackground.copy(alpha = 0.3f),
            fontSize = 13.sp,
        )
    }
}

@Composable
private fun ImageQualityRow(iq: ImageQualityState) {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(scheme.surface)
            .border(1.dp, scheme.outline, RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = Strings.ACCORDION_COLLAPSED_GLYPH,
            color = scheme.onSurfaceVariant,
            fontSize = 13.sp,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = iq.title,
            color = scheme.onSurfaceVariant,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = Strings.IQ_SUBTITLE,
            color = scheme.onSurfaceVariant.copy(alpha = 0.6f),
            fontSize = 11.sp,
        )
    }
}

@Composable
private fun ReminderStrip(text: String) {
    val scheme = MaterialTheme.colorScheme
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(scheme.surface)
            .border(1.dp, scheme.outline, RoundedCornerShape(8.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(scheme.secondary),
        )
        Spacer(Modifier.width(12.dp))
        Text(
            text = text,
            color = scheme.onBackground,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun ShutterButton() {
    val scheme = MaterialTheme.colorScheme
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
                .border(1.dp, scheme.outline, CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_btn_shutter),
                contentDescription = Strings.SHUTTER_DESC,
                tint = scheme.onBackground,
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
    val scheme = MaterialTheme.colorScheme
    val color = when (chip.kind) {
        AlertChipKind.BSD_LEFT, AlertChipKind.BSD_RIGHT -> scheme.tertiary
        AlertChipKind.RCW -> scheme.error
        AlertChipKind.TILT -> scheme.error
        AlertChipKind.CLEAR -> scheme.onSurfaceVariant
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
