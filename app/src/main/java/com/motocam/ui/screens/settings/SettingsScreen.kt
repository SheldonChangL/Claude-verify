package com.motocam.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motocam.R
import com.motocam.ui.Strings
import com.motocam.ui.components.SettingsSwitch
import com.motocam.ui.components.SettingsToggle
import com.motocam.ui.components.SettingsValueText
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoColors
import com.motocam.ui.theme.MotoTheme

@Composable
fun SettingsScreen() {
    val spacing = LocalSpacing.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MotoColors.Background)
            .verticalScroll(rememberScrollState()),
    ) {
        StatusBar()
        PageTitle()
        Spacer(Modifier.height(spacing.sm))

        GroupLabel(Strings.SETTINGS_GROUP_DEVICE)
        DeviceCard()
        Spacer(Modifier.height(spacing.md))
        StorageCard()

        Spacer(Modifier.height(spacing.lg))
        GroupLabel(Strings.SETTINGS_GROUP_LIVE)
        LiveViewGroup()

        Spacer(Modifier.height(spacing.lg))
        GroupLabel(Strings.SETTINGS_GROUP_ALERTS)
        AlertsGroup()

        Spacer(Modifier.height(spacing.lg))
        GroupLabel(Strings.SETTINGS_GROUP_EVENT_REC)
        EventRecGroup()

        Spacer(Modifier.height(spacing.lg))
        GroupLabel(Strings.SETTINGS_GROUP_STORAGE)
        StorageGroup()

        Spacer(Modifier.height(spacing.lg))
        GroupLabel(Strings.SETTINGS_GROUP_FIRMWARE)
        FirmwareGroup()

        Spacer(Modifier.height(spacing.lg))
        GroupLabel(Strings.SETTINGS_GROUP_VERSION)
        VersionGroup()

        Spacer(Modifier.height(spacing.xl))
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
            .padding(horizontal = 14.dp, vertical = 4.dp),
    ) {
        Text(
            text = Strings.SETTINGS_TITLE,
            color = MotoColors.Foreground,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun GroupLabel(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp),
    ) {
        Text(
            text = text,
            color = MotoColors.Muted,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
private fun GroupCard(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MotoColors.Surface)
            .border(1.dp, MotoColors.Outline, RoundedCornerShape(14.dp)),
        content = content,
    )
}

@Composable
private fun RowDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
            .height(1.dp)
            .background(MotoColors.Outline),
    )
}

@Composable
private fun DeviceCard() {
    GroupCard {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MotoColors.Background)
                    .border(1.dp, MotoColors.Outline, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_device_camera),
                    contentDescription = null,
                    tint = MotoColors.Foreground,
                    modifier = Modifier.size(24.dp),
                )
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = Strings.SETTINGS_DEVICE_NAME,
                    color = MotoColors.Foreground,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = Strings.SETTINGS_DEVICE_FIRMWARE,
                    color = MotoColors.Muted,
                    fontSize = 13.sp,
                )
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(MotoColors.Success),
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = Strings.SETTINGS_DEVICE_CONNECTED,
                        color = MotoColors.Success,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}

@Composable
private fun StorageCard() {
    GroupCard {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = Strings.SETTINGS_STORAGE_LABEL,
                    color = MotoColors.Foreground,
                    fontSize = 17.sp,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = Strings.SETTINGS_STORAGE_TOTAL,
                    color = MotoColors.Muted,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(99.dp))
                    .background(MotoColors.Outline),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.42f)
                        .height(8.dp)
                        .clip(RoundedCornerShape(99.dp))
                        .background(MotoColors.Accent),
                )
            }
            Spacer(Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                LegendDot(color = MotoColors.Accent)
                Spacer(Modifier.width(4.dp))
                Text(
                    text = Strings.SETTINGS_STORAGE_USED,
                    color = MotoColors.Muted,
                    fontSize = 13.sp,
                )
                Spacer(Modifier.width(12.dp))
                LegendDot(color = MotoColors.Outline.copy(alpha = 0.8f))
                Spacer(Modifier.width(4.dp))
                Text(
                    text = Strings.SETTINGS_STORAGE_FREE,
                    color = MotoColors.Muted,
                    fontSize = 13.sp,
                )
            }
        }
    }
}

@Composable
private fun LegendDot(color: Color) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .clip(CircleShape)
            .background(color),
    )
}

@Composable
private fun LiveViewGroup() {
    var displayMode by remember { mutableStateOf(0) }
    var powerSave by remember { mutableStateOf(1) }
    GroupCard {
        SettingsToggle(
            label = Strings.SETTINGS_DISPLAY_MODE,
            value = null,
            trailing = {
                SegmentedControl(
                    options = listOf(Strings.SETTINGS_DISPLAY_LIVE, Strings.SETTINGS_DISPLAY_VIRTUAL),
                    selected = displayMode,
                    onSelect = { displayMode = it },
                )
            },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_POWERSAVE,
            value = null,
            trailing = {
                SegmentedControl(
                    options = listOf(
                        Strings.SETTINGS_POWERSAVE_1,
                        Strings.SETTINGS_POWERSAVE_3,
                        Strings.SETTINGS_POWERSAVE_5,
                        Strings.SETTINGS_POWERSAVE_ALWAYS,
                    ),
                    selected = powerSave,
                    onSelect = { powerSave = it },
                )
            },
        )
    }
}

@Composable
private fun AlertsGroup() {
    var bsd by remember { mutableStateOf(true) }
    var rcw by remember { mutableStateOf(true) }
    var fall by remember { mutableStateOf(true) }
    var bsdSens by remember { mutableStateOf(1) }
    var rcwSens by remember { mutableStateOf(1) }
    val sensOptions = listOf(Strings.SETTINGS_SENS_LOW, Strings.SETTINGS_SENS_MID, Strings.SETTINGS_SENS_HIGH)
    GroupCard {
        SettingsToggle(
            label = Strings.SETTINGS_BSD_ENABLE,
            value = null,
            trailing = { SettingsSwitch(checked = bsd, onCheckedChange = { bsd = it }) },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_BSD_SENSITIVITY,
            value = null,
            trailing = {
                SegmentedControl(options = sensOptions, selected = bsdSens, onSelect = { bsdSens = it })
            },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_RCW_ENABLE,
            value = null,
            trailing = { SettingsSwitch(checked = rcw, onCheckedChange = { rcw = it }) },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_RCW_SENSITIVITY,
            value = null,
            trailing = {
                SegmentedControl(options = sensOptions, selected = rcwSens, onSelect = { rcwSens = it })
            },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_FALL_ENABLE,
            value = null,
            trailing = { SettingsSwitch(checked = fall, onCheckedChange = { fall = it }) },
        )
    }
}

@Composable
private fun EventRecGroup() {
    GroupCard {
        SettingsToggle(
            label = Strings.SETTINGS_EVENT_PRE,
            value = null,
            trailing = { SettingsValueText(text = Strings.SETTINGS_EVENT_PRE_VALUE) },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_EVENT_POST,
            value = null,
            trailing = { SettingsValueText(text = Strings.SETTINGS_EVENT_POST_VALUE) },
        )
    }
}

@Composable
private fun StorageGroup() {
    var loopCap by remember { mutableStateOf(1) }
    GroupCard {
        SettingsToggle(
            label = Strings.SETTINGS_LOOP_CAP,
            value = null,
            trailing = {
                SegmentedControl(
                    options = listOf(
                        Strings.SETTINGS_LOOP_2G,
                        Strings.SETTINGS_LOOP_4G,
                        Strings.SETTINGS_LOOP_6G,
                        Strings.SETTINGS_LOOP_8G,
                    ),
                    selected = loopCap,
                    onSelect = { loopCap = it },
                )
            },
        )
    }
}

@Composable
private fun FirmwareGroup() {
    var autoUpdate by remember { mutableStateOf(true) }
    GroupCard {
        SettingsToggle(
            label = Strings.SETTINGS_FW_CHECK,
            value = null,
            trailing = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = MotoColors.Muted,
                    modifier = Modifier.size(16.dp),
                )
            },
            onClick = {},
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_FW_AUTO,
            value = null,
            trailing = { SettingsSwitch(checked = autoUpdate, onCheckedChange = { autoUpdate = it }) },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_FW_RESET,
            value = null,
            trailing = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = MotoColors.Muted,
                    modifier = Modifier.size(16.dp),
                )
            },
            onClick = {},
        )
    }
}

@Composable
private fun VersionGroup() {
    GroupCard {
        SettingsToggle(
            label = Strings.SETTINGS_VERSION_APP,
            value = null,
            trailing = { SettingsValueText(text = Strings.SETTINGS_VERSION_APP_VALUE) },
        )
        RowDivider()
        SettingsToggle(
            label = Strings.SETTINGS_VERSION_DEVICE,
            value = null,
            trailing = { SettingsValueText(text = Strings.SETTINGS_VERSION_DEVICE_VALUE) },
        )
    }
}

@Composable
private fun SegmentedControl(
    options: List<String>,
    selected: Int,
    onSelect: (Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(99.dp))
            .background(MotoColors.Background)
            .border(1.dp, MotoColors.Outline, RoundedCornerShape(99.dp))
            .padding(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        options.forEachIndexed { index, label ->
            val isSelected = index == selected
            val bg = if (isSelected) MotoColors.Accent else Color.Transparent
            val fg = if (isSelected) MotoColors.OnAccent else MotoColors.Muted
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(99.dp))
                    .background(bg)
                    .clickable { onSelect(index) }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
            ) {
                Text(
                    text = label,
                    color = fg,
                    fontSize = 12.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF06080F, widthDp = 390, heightDp = 1400)
@Composable
private fun SettingsScreenPreview() {
    MotoTheme {
        SettingsScreen()
    }
}
