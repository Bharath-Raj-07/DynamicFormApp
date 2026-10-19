package com.example.leadcapture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.leadcapture.presentation.leadform.LeadFormRoute
import com.example.leadcapture.core.ui.theme.LeadCaptureTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LeadCaptureTheme {
                LeadFormRoute()
            }
        }
    }
}