package com.motocam.ui.screens.alerts

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
import com.motocam.ui.components.AlertIconType
import com.motocam.ui.components.AlertRow
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoColors
import com.motocam.ui.theme.MotoTheme

private data class AlertItem(
    val iconType: AlertIconType,
    val title: String,
    val time: String,
)

private val MockAlerts: List<AlertItem> = listOf(
    AlertItem(AlertIconType.Bsd, "左側盲點警示", "9:46 PM"),
    AlertItem(AlertIconType.Rcw, "後方防撞警示", "9:32 PM"),
    AlertItem(AlertIconType.Bsd, "右側盲點警示", "9:05 PM"),
    AlertItem(AlertIconType.Tilt, "車輛傾倒偵測", "8:31 PM"),
    AlertItem(AlertIconType.Bsd, "左側盲點警示", "7:48 PM"),
    AlertItem(AlertIconType.Rcw, "後方防撞警示", "7:22 PM"),
    AlertItem(AlertIconType.Bsd, "右側盲點警示", "6:57 PM"),
    AlertItem(AlertIconType.Bsd, "左側盲點警示", "6:34 PM"),
    AlertItem(AlertIconType.Bsd, "右側盲點警示", "6:11 PM"),
    AlertItem(AlertIconType.Rcw, "後方防撞警示", "5:45 PM"),
)

@Composable
fun AlertsScreen() {
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
            items(MockAlerts) { item ->
                AlertRow(
                    iconType = item.iconType,
                    title = item.title,
                    time = item.time,
                    onJump = {},
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
            text = "21:54",
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
            text = "觸發事件",
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
        FilterChip(label = "全部 10", selected = true)
        FilterChip(label = "BSD …", selected = false)
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
            text = "今天 · 2026-05-14",
            color = MotoColors.Muted,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF06080F, widthDp = 390, heightDp = 844)
@Composable
private fun AlertsScreenPreview() {
    MotoTheme {
        AlertsScreen()
    }
}
