package com.example.cse_535_project_2_jet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cse_535_project_2_jet.ui.screens.GameScreen
import com.example.cse_535_project_2_jet.ui.screens.PastGameScreen
import com.example.cse_535_project_2_jet.ui.screens.SettingsScreen
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel

@Composable
fun AppNavHost(navController: NavHostController) {
    // Get the TicTacToeViewModel instance
    val ticTacToeViewModel: TicTacToeViewModel = viewModel()

    NavHost(navController = navController, startDestination = "game") {
        composable("game") {
            GameScreen(navController, ticTacToeViewModel)
        }
        composable("past_game") {
            PastGameScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}
