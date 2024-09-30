package com.example.cse_535_project_2_jet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cse_535_project_2_jet.ui.screens.GameScreen
import com.example.cse_535_project_2_jet.ui.screens.PastGameScreen
import com.example.cse_535_project_2_jet.ui.screens.SettingsScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "game") {
        composable("game") {
            GameScreen(navController)
        }
        composable("past_game") {
            PastGameScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}