package com.motocam.ui.nav

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MotoNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { Text("home") }
        composable("playback") { Text("playback") }
        composable("alerts") { Text("alerts") }
        composable("settings") { Text("settings") }
    }
}
