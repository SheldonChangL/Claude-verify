package com.motocam.ui.nav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.motocam.R
import com.motocam.ui.theme.MotoColors

private data class MotoTab(
    val route: String,
    val label: String,
    val iconRes: Int,
)

private val tabs = listOf(
    MotoTab("home", "首頁", R.drawable.ic_home_active),
    MotoTab("alerts", "警示", R.drawable.ic_alert),
    MotoTab("playback", "回放", R.drawable.ic_playback),
    MotoTab("settings", "設定", R.drawable.ic_settings),
)

@Composable
fun BottomNavBar(selected: String, onSelect: (String) -> Unit) {
    NavigationBar(
        containerColor = MotoColors.Background,
        contentColor = MotoColors.Foreground,
    ) {
        tabs.forEach { tab ->
            NavigationBarItem(
                selected = selected == tab.route,
                onClick = { onSelect(tab.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = tab.iconRes),
                        contentDescription = tab.label,
                    )
                },
                label = { Text(tab.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MotoColors.Accent,
                    selectedTextColor = MotoColors.Accent,
                    indicatorColor = MotoColors.Surface,
                    unselectedIconColor = MotoColors.Muted,
                    unselectedTextColor = MotoColors.Muted,
                ),
            )
        }
    }
}
