package com.motocam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        Surface(modifier = Modifier.fillMaxSize()) {
            Text(text = "Bootstrapped")
        }
    }
}
