package com.motocam.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.motocam.R
import com.motocam.ui.Strings
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoColors

enum class AlertIconType { Bsd, Rcw, Tilt }

data class AlertChip(val label: String, val textColor: Color, val background: Color)

private val AlertIconType.iconRes: Int
    get() = when (this) {
        AlertIconType.Bsd -> R.drawable.ic_alert_bsd
        AlertIconType.Rcw -> R.drawable.ic_alert_rcw
        AlertIconType.Tilt -> R.drawable.ic_alert_tilt
    }

private val AlertIconType.accent: Color
    get() = when (this) {
        AlertIconType.Bsd -> MotoColors.Warning
        AlertIconType.Rcw, AlertIconType.Tilt -> MotoColors.Danger
    }

private val AlertIconType.bubble: Color
    get() = when (this) {
        AlertIconType.Bsd -> MotoColors.IconBubbleWarning
        AlertIconType.Rcw, AlertIconType.Tilt -> MotoColors.IconBubbleDanger
    }

fun AlertIconType.defaultChip(): AlertChip = when (this) {
    AlertIconType.Bsd -> AlertChip(Strings.TAG_BSD, MotoColors.Warning, MotoColors.WarningSoft)
    AlertIconType.Rcw -> AlertChip(Strings.TAG_RCW, MotoColors.Danger, MotoColors.DangerSoft)
    AlertIconType.Tilt -> AlertChip(Strings.TAG_FALL, MotoColors.Danger, MotoColors.DangerSoft)
}

@Composable
fun AlertRow(
    iconType: AlertIconType,
    title: String,
    time: String,
    modifier: Modifier = Modifier,
    chip: AlertChip? = null,
    onJump: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    val spacing = LocalSpacing.current
    val resolvedChip = chip ?: iconType.defaultChip()

    val rowModifier = modifier
        .clip(MaterialTheme.shapes.large)
        .background(MotoColors.Surface)
        .border(spacing.hairline, MotoColors.Outline, MaterialTheme.shapes.large)
        .let { if (onClick != null) it.clickable(onClick = onClick) else it }
        .padding(spacing.md)

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        AlertAvatar(iconType)

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = MotoColors.Foreground,
                maxLines = 1,
            )
            Row(
                modifier = Modifier.padding(top = spacing.xs),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            ) {
                Text(
                    text = time,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MotoColors.Muted,
                )
                AlertChipPill(resolvedChip)
            }
        }

        if (onJump != null) {
            JumpButton(onJump)
        }
    }
}

@Composable
private fun AlertAvatar(iconType: AlertIconType) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .size(spacing.avatarMd)
            .clip(CircleShape)
            .background(iconType.bubble),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(iconType.iconRes),
            contentDescription = null,
            tint = iconType.accent,
            modifier = Modifier.size(spacing.iconMd),
        )
    }
}

@Composable
private fun AlertChipPill(chip: AlertChip) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(chip.background)
            .border(spacing.hairline, chip.textColor.copy(alpha = 0.6f), MaterialTheme.shapes.extraLarge)
            .padding(horizontal = spacing.sm, vertical = spacing.hairline),
    ) {
        Text(
            text = chip.label,
            style = MaterialTheme.typography.labelMedium,
            color = chip.textColor,
        )
    }
}

@Composable
private fun JumpButton(onClick: () -> Unit) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .size(spacing.avatarMd)
            .clip(CircleShape)
            .background(MotoColors.Outline)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_chevron_right),
            contentDescription = Strings.TAB_PLAYBACK,
            tint = MotoColors.Foreground,
            modifier = Modifier.size(spacing.iconMd),
        )
    }
}
