package com.motocam.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoColors

enum class VideoTagStyle { Bsd, Rcw, Fall, Shot }

data class VideoTag(val label: String, val style: VideoTagStyle)

private val VideoTagStyle.textColor: Color
    get() = when (this) {
        VideoTagStyle.Bsd -> MotoColors.Warning
        VideoTagStyle.Rcw, VideoTagStyle.Fall -> MotoColors.Danger
        VideoTagStyle.Shot -> MotoColors.Foreground
    }

private val VideoTagStyle.background: Color
    get() = when (this) {
        VideoTagStyle.Bsd -> Color(0x2EFF9F0A)
        VideoTagStyle.Rcw, VideoTagStyle.Fall -> Color(0x2EFF4444)
        VideoTagStyle.Shot -> Color(0x24FFFFFF)
    }

@Composable
fun VideoRow(
    thumbnail: Painter?,
    title: String,
    timestamp: String,
    modifier: Modifier = Modifier,
    duration: String? = null,
    sizeText: String? = null,
    tag: VideoTag? = null,
    onClick: (() -> Unit)? = null,
) {
    val spacing = LocalSpacing.current
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
        Thumbnail(painter = thumbnail, duration = duration)

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
                horizontalArrangement = Arrangement.spacedBy(spacing.xs),
            ) {
                if (tag != null) TagPill(tag)
                Text(
                    text = timestamp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MotoColors.Muted,
                )
            }
            if (sizeText != null) {
                Text(
                    text = sizeText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MotoColors.Muted.copy(alpha = 0.6f),
                    modifier = Modifier.padding(top = spacing.xs),
                )
            }
        }
    }
}

@Composable
private fun Thumbnail(painter: Painter?, duration: String?) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .size(width = spacing.thumbWidth, height = spacing.thumbHeight)
            .clip(MaterialTheme.shapes.small)
            .background(Color(0xFF0A0A0C)),
    ) {
        if (painter != null) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(width = spacing.thumbWidth, height = spacing.thumbHeight),
                contentScale = ContentScale.Crop,
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(spacing.iconSm)
                .clip(CircleShape)
                .background(Color(0xA6000000)),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "▶",
                style = MaterialTheme.typography.labelSmall,
                color = MotoColors.Foreground,
            )
        }
        if (duration != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(spacing.xs)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(Color(0xB3000000))
                    .padding(horizontal = spacing.xs, vertical = spacing.hairline),
            ) {
                Text(
                    text = duration,
                    style = MaterialTheme.typography.labelSmall,
                    color = MotoColors.Foreground,
                )
            }
        }
    }
}

@Composable
private fun TagPill(tag: VideoTag) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(tag.style.background)
            .padding(horizontal = spacing.sm, vertical = spacing.hairline),
    ) {
        Text(
            text = tag.label,
            style = MaterialTheme.typography.labelMedium,
            color = tag.style.textColor,
        )
    }
}
