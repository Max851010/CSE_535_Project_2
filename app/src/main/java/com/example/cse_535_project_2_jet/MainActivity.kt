package com.example.cse_535_project_2_jet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cse_535_project_2_jet.database.GameDatabase
import com.example.cse_535_project_2_jet.ui.screens.MainScreen
import com.example.cse_535_project_2_jet.ui.theme.CSE_535_Project_2_jetTheme

class MainActivity : ComponentActivity() {
    private lateinit var gameDatabase: GameDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameDatabase = GameDatabase.getDatabase(this)
        enableEdgeToEdge()
        setContent {
            CSE_535_Project_2_jetTheme {
                MainScreen()
            }
        }
    }
}