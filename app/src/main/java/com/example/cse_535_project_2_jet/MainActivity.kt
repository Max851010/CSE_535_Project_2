package com.example.cse_535_project_2_jet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.cse_535_project_2_jet.navigation.AppNavHost
import com.example.cse_535_project_2_jet.ui.screens.MainScreen
import com.example.cse_535_project_2_jet.ui.theme.CSE_535_Project_2_jetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CSE_535_Project_2_jetTheme {
                MainScreen()
            }
        }
    }
}