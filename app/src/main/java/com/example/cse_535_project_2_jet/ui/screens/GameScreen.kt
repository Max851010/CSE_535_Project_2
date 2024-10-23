package com.example.cse_535_project_2_jet.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.database.GameDatabase

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cse_535_project_2_jet.ui.components.TicTacToeViewModelFactory
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel
import com.example.cse_535_project_2_jet.viewModels.DataBaseViewModel


@Composable
fun GameScreen(
    navController: NavHostController,
    databaseViewModel: DataBaseViewModel = viewModel()// Default ViewModel provider
)  {
    val isLoading by databaseViewModel.isLoading.collectAsState()
    LaunchedEffect(Unit) {
        databaseViewModel.loadSettings()
    }
    if (isLoading || databaseViewModel.setting == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        val viewModel: TicTacToeViewModel = viewModel(
            factory = TicTacToeViewModelFactory(databaseViewModel)
        )
        val board = viewModel.board
        val currentPlayer = viewModel.currentPlayer
        val winner = viewModel.winner

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Current Player: ${
                    if (databaseViewModel.setting?.type == '1') {
                        // Playing against AI
                        if (currentPlayer == "X") {
                            "Player"  // Human
                        } else {
                            "AI"  // AI's turn
                        }
                    } else {
                        // Player vs Player mode
                        if (currentPlayer == "X") {
                            "Player 1"
                        } else {
                            "Player 2"
                        }
                    }
                }"
            )


            Spacer(modifier = Modifier.height(16.dp))

            // 3x3 Grid for the Tic-Tac-Toe board
            for (i in 0..2) {
                Row {
                    for (j in 0..2) {
                        val index = i * 3 + j
                        OutlinedButton(
                            onClick = { viewModel.makeMove(index) },
                            shape = RectangleShape,
                            modifier = Modifier.size(100.dp)
                        ) {
                            Text(text = board[index], style = MaterialTheme.typography.headlineLarge)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Show winner message if there is one
            if (winner != null) {
                if (winner == "Draw") {
                    Text(text = "Draw Game", style = MaterialTheme.typography.titleMedium)
                } else {
                    val winnerText = if (databaseViewModel.setting?.type == '1' && winner == "Player 1") {
                        "Winner: AI"
                    } else {
                        "Winner: $winner"
                    }
                    Text(text = winnerText, style = MaterialTheme.typography.titleMedium)
                }
            }

            // Always show reset button
            Button(onClick = { viewModel.resetGame() }) {
                Text("Reset")
            }
        }

    }
}
