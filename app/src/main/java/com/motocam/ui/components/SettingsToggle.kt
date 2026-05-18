package com.motocam.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.motocam.ui.theme.LocalSpacing
import com.motocam.ui.theme.MotoColors

@Composable
fun SettingsToggle(
    label: String,
    value: String?,
    trailing: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    val spacing = LocalSpacing.current

    val rowModifier = modifier
        .defaultMinSize(minHeight = 52.dp)
        .let { if (onClick != null) it.clickable(onClick = onClick) else it }
        .padding(horizontal = 14.dp, vertical = spacing.sm)

    Row(
        modifier = rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                color = MotoColors.Foreground,
                maxLines = 1,
            )
            if (!value.isNullOrEmpty()) {
                Text(
                    text = value,
                    fontSize = 13.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MotoColors.Muted,
                    maxLines = 1,
                )
            }
        }
        trailing()
    }
}

@Composable
fun SettingsValueText(text: String) {
    Text(
        text = text,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold,
        color = MotoColors.Muted,
    )
}

@Composable
fun SettingsSwitch(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        colors = SwitchDefaults.colors(
            checkedThumbColor = MotoColors.Foreground,
            checkedTrackColor = MotoColors.Accent,
            checkedBorderColor = MotoColors.Accent,
            uncheckedThumbColor = MotoColors.Foreground,
            uncheckedTrackColor = MotoColors.Outline,
            uncheckedBorderColor = MotoColors.Outline,
        ),
    )
}
