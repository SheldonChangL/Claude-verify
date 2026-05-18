package com.motocam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import com.motocam.ui.nav.BottomNavBar
import com.motocam.ui.nav.MotoNavHost
import com.motocam.ui.theme.MotoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MotoCamApp()
        }
    }
}

@Composable
fun MotoCamApp() {
    MotoTheme {
        val navController = rememberNavController()
        var selectedRoute by rememberSaveable { mutableStateOf("home") }
        val backStackEntry by navController.currentBackStackEntryAsState()
        LaunchedEffect(backStackEntry) {
            backStackEntry?.destination?.route?.let { selectedRoute = it }
        }
        Scaffold(
            bottomBar = {
                BottomNavBar(
                    selected = selectedRoute,
                    onSelect = { route ->
                        if (route != selectedRoute) {
                            selectedRoute = route
                            navController.navigate(route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                )
            },
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                MotoNavHost(navController)
            }
        }
    }
}
