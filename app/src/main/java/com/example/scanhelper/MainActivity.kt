package com.example.scanhelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scanhelper.ui.main.MainMenuScreen
import com.example.scanhelper.ui.theme.ScanHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScanHelperTheme {
                MainMenuScreen()
            }
        }
    }
}