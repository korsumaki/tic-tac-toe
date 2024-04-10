package com.korsumaki.tictactoe

import App
import AppWithParams
import GameViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App()
        }
    }
}

// @PreviewScreenSizes
// @PreviewFontScales
// @PreviewLightDark
// @PreviewDynamicColors
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppAndroidPreview() {
    val viewModel = GameViewModel(5,5)
    AppWithParams(
        viewModel,
        cross = { Text(" X ") },
        circle = { Text(" O ") },
        empty = { Text("   ") }
    )
}