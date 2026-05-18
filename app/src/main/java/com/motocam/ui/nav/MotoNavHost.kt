package com.motocam.ui.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.motocam.ui.screens.alerts.AlertsScreen
import com.motocam.ui.screens.home.HomeScreen
import com.motocam.ui.screens.playback.PlaybackScreen

@Composable
fun MotoNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("playback") { PlaybackScreen() }
        composable("alerts") { AlertsScreen() }
        composable("settings") { Text("settings") }
    }
}
