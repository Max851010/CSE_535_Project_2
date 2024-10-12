package com.example.cse_535_project_2_jet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.database.GameDatabase

@Composable
fun PastGameScreen(navController: NavHostController) {
    lateinit var gameDatabase: GameDatabase
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Past Game Screen")
    }
}