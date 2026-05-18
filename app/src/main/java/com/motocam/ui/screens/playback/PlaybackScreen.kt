package com.motocam.ui.screens.playback

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.motocam.R
import com.motocam.ui.Strings
import com.motocam.ui.components.VideoRow
import com.motocam.ui.components.VideoTag
import com.motocam.ui.components.VideoTagStyle
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoColors
import com.motocam.ui.theme.MotoTheme

private data class PlaybackItem(
    val filename: String,
    val time: String,
    val duration: String,
    val sizeText: String,
    val tag: VideoTag,
)

private val MockItems: List<PlaybackItem> = listOf(
    PlaybackItem("20260514_2146_BSD.ts", "9:46 PM", "00:18", "12.4 MB", VideoTag(Strings.TAG_BSD, VideoTagStyle.Bsd)),
    PlaybackItem("20260514_2132_RCW.ts", "9:32 PM", "00:22", "15.1 MB", VideoTag(Strings.TAG_RCW, VideoTagStyle.Rcw)),
    PlaybackItem("20260514_2118_SHOT.jpg", "9:18 PM", "—", "2.1 MB", VideoTag(Strings.TAG_SHOT, VideoTagStyle.Shot)),
    PlaybackItem("20260514_2054_BSD.ts", "8:54 PM", "00:16", "10.8 MB", VideoTag(Strings.TAG_BSD, VideoTagStyle.Bsd)),
    PlaybackItem("20260514_2031_FALL.ts", "8:31 PM", "00:35", "23.6 MB", VideoTag(Strings.TAG_FALL, VideoTagStyle.Fall)),
    PlaybackItem("20260514_1948_BSD.ts", "7:48 PM", "00:14", "9.7 MB", VideoTag(Strings.TAG_BSD, VideoTagStyle.Bsd)),
    PlaybackItem("20260514_1922_RCW.ts", "7:22 PM", "00:20", "13.8 MB", VideoTag(Strings.TAG_RCW, VideoTagStyle.Rcw)),
    PlaybackItem("20260514_1857_SHOT.jpg", "6:57 PM", "—", "1.9 MB", VideoTag(Strings.TAG_SHOT, VideoTagStyle.Shot)),
    PlaybackItem("20260514_1834_BSD.ts", "6:34 PM", "00:17", "11.5 MB", VideoTag(Strings.TAG_BSD, VideoTagStyle.Bsd)),
    PlaybackItem("20260514_1811_BSD.ts", "6:11 PM", "00:19", "12.9 MB", VideoTag(Strings.TAG_BSD, VideoTagStyle.Bsd)),
    PlaybackItem("20260514_1745_RCW.ts", "5:45 PM", "00:24", "16.4 MB", VideoTag(Strings.TAG_RCW, VideoTagStyle.Rcw)),
    PlaybackItem("20260514_1720_SHOT.jpg", "5:20 PM", "—", "2.3 MB", VideoTag(Strings.TAG_SHOT, VideoTagStyle.Shot)),
)

@Composable
fun PlaybackScreen() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MotoColors.Background),
    ) {
        StatusBar()
        PageTitle()
        FilterChipRow()
        Spacer(Modifier.height(spacing.md))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            item { DaySectionHeader() }
            items(MockItems) { item ->
                VideoRow(
                    thumbnail = null,
                    title = item.filename,
                    timestamp = item.time,
                    duration = item.duration,
                    sizeText = "${item.sizeText} · ${item.duration}",
                    tag = item.tag,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            item { Spacer(Modifier.height(spacing.lg)) }
        }
    }
}

@Composable
private fun StatusBar() {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.xl, vertical = spacing.md),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = Strings.CLOCK_DEFAULT,
            color = MotoColors.Foreground,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun PageTitle() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = Strings.PLAYBACK_TITLE,
            color = MotoColors.Foreground,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun FilterChipRow() {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        DateChip()
        FilterChip(label = Strings.PLAYBACK_FILTER_ALL, selected = true)
        FilterChip(label = Strings.PLAYBACK_FILTER_BSD, selected = false)
        Spacer(Modifier.weight(1f))
        FilterChip(label = Strings.PLAYBACK_FILTER_SELECT, selected = false)
    }
}

@Composable
private fun DateChip() {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .border(1.dp, MotoColors.Outline, MaterialTheme.shapes.extraLarge)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = null,
            tint = MotoColors.Muted,
            modifier = Modifier.size(14.dp),
        )
        Spacer(Modifier.width(spacing.xs))
        Icon(
            painter = painterResource(id = R.drawable.ic_chevron_down),
            contentDescription = null,
            tint = MotoColors.Muted,
            modifier = Modifier.size(10.dp),
        )
    }
}

@Composable
private fun FilterChip(label: String, selected: Boolean) {
    val bg = if (selected) MotoColors.Accent else Color.Transparent
    val fg = if (selected) MotoColors.OnAccent else MotoColors.Muted
    val borderColor = if (selected) MotoColors.Accent else MotoColors.Outline
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(bg)
            .border(1.dp, borderColor, MaterialTheme.shapes.extraLarge)
            .padding(horizontal = 12.dp, vertical = 6.dp),
    ) {
        Text(
            text = label,
            color = fg,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
        )
    }
}

@Composable
private fun DaySectionHeader() {
    val spacing = LocalSpacing.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MotoColors.Muted.copy(alpha = 0.6f)),
        )
        Spacer(Modifier.width(spacing.sm))
        Text(
            text = Strings.PLAYBACK_DAY_HEADER,
            color = MotoColors.Muted,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF06080F, widthDp = 390, heightDp = 844)
@Composable
private fun PlaybackScreenPreview() {
    MotoTheme {
        PlaybackScreen()
    }
}
