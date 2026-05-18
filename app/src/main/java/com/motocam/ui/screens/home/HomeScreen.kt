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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.motocam.R
import com.motocam.ui.Strings
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoTheme

@Composable
fun HomeScreen(state: HomeUiState = HomeUiState.mock()) {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
    ) {
        StatusBar(clock = state.clock, recording = state.recording)
        SpeedHero(speed = state.speedKmh, unit = state.speedUnit)
        Spacer(Modifier.height(spacing.sm))
        if (state.banner.visible) {
            AlertBannerCard(state.banner)
            Spacer(Modifier.height(spacing.sm))
        }
        CameraViewportCard(state.camera)
        Spacer(Modifier.height(spacing.md))
        ImageQualityRow(state.imageQuality)
        Spacer(Modifier.height(spacing.md))
        ReminderStrip(state.reminderText)
        Spacer(Modifier.height(spacing.md))
        ShutterButton()
        Spacer(Modifier.height(spacing.md))
        AlertChipRow(state.alertChips)
        Spacer(Modifier.height(spacing.lg))
    }
}

@Composable
private fun StatusBar(clock: String, recording: Boolean) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.xl, vertical = spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = clock,
            color = scheme.onBackground,
            style = typo.bodyLarge,
            modifier = Modifier.weight(1f),
        )
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
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
                    style = typo.labelSmall,
                )
            }
        }
    }
}

@Composable
private fun SpeedHero(speed: Int, unit: String) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = spacing.xl, end = spacing.xl, top = spacing.xs, bottom = spacing.xs),
        verticalAlignment = Alignment.Bottom,
    ) {
        Text(
            text = speed.toString(),
            color = scheme.primary,
            style = typo.displayLarge,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = unit,
            color = scheme.primary,
            style = typo.headlineLarge,
            modifier = Modifier.padding(bottom = 14.dp),
        )
    }
}

@Composable
private fun CameraViewportCard(camera: CameraOverlayState) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(scheme.background)
            .border(1.dp, scheme.outline, MaterialTheme.shapes.medium)
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
                .padding(spacing.sm)
                .clip(MaterialTheme.shapes.small)
                .background(
                    if (camera.recording) scheme.error.copy(alpha = 0.85f)
                    else scheme.onSurfaceVariant.copy(alpha = 0.8f),
                )
                .padding(horizontal = spacing.sm, vertical = 3.dp),
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
                    style = typo.labelSmall,
                )
            }
        }

        Text(
            text = camera.timestamp,
            color = scheme.tertiary,
            style = typo.bodySmall,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 10.dp, bottom = 8.dp),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(spacing.sm)
                .size(28.dp)
                .clip(MaterialTheme.shapes.small)
                .background(scheme.background.copy(alpha = 0.55f))
                .border(1.dp, scheme.outline, MaterialTheme.shapes.small),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = Strings.FULLSCREEN_GLYPH,
                color = scheme.onBackground,
                style = typo.titleSmall,
            )
        }
    }
}

@Composable
private fun AlertBannerCard(banner: AlertBannerState) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
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
            .clip(MaterialTheme.shapes.large)
            .background(bg)
            .border(1.dp, accent.copy(alpha = 0.45f), MaterialTheme.shapes.large)
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(26.dp)
                .clip(MaterialTheme.shapes.small)
                .background(accent.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "!", color = accent, style = typo.titleMedium)
        }
        Spacer(Modifier.width(10.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = banner.title,
                color = accent,
                style = typo.titleMedium,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = banner.subtitle,
                color = scheme.onBackground.copy(alpha = 0.55f),
                style = typo.bodyMedium,
            )
        }
        Text(
            text = banner.time,
            color = scheme.onBackground.copy(alpha = 0.3f),
            style = typo.bodyMedium,
        )
    }
}

@Composable
private fun ImageQualityRow(iq: ImageQualityState) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(scheme.surface)
            .border(1.dp, scheme.outline, MaterialTheme.shapes.medium)
            .padding(horizontal = 14.dp, vertical = spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = Strings.ACCORDION_COLLAPSED_GLYPH,
            color = scheme.onSurfaceVariant,
            style = typo.bodyMedium,
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = iq.title,
            color = scheme.onSurfaceVariant,
            style = typo.labelLarge,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = Strings.IQ_SUBTITLE,
            color = scheme.onSurfaceVariant.copy(alpha = 0.6f),
            style = typo.bodySmall,
        )
    }
}

@Composable
private fun ReminderStrip(text: String) {
    val scheme = MaterialTheme.colorScheme
    val typo = MaterialTheme.typography
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(MaterialTheme.shapes.small)
            .background(scheme.surface)
            .border(1.dp, scheme.outline, MaterialTheme.shapes.small)
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
            style = typo.bodyLarge,
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
    val typo = MaterialTheme.typography
    val color = when (chip.kind) {
        AlertChipKind.BSD_LEFT, AlertChipKind.BSD_RIGHT -> scheme.tertiary
        AlertChipKind.RCW -> scheme.error
        AlertChipKind.TILT -> scheme.error
        AlertChipKind.CLEAR -> scheme.onSurfaceVariant
    }
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(color.copy(alpha = 0.14f))
            .border(1.dp, color.copy(alpha = 0.4f), MaterialTheme.shapes.extraLarge)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = chip.label,
            color = color,
            style = typo.labelMedium,
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
