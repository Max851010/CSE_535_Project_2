package com.example.cse_535_project_2_jet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel

@Composable
fun GameScreen(
    navController: NavHostController,
    viewModel: TicTacToeViewModel = viewModel() // Default ViewModel provider
)  {
    val board = viewModel.board
    val currentPlayer = viewModel.currentPlayer
    val winner = viewModel.winner

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Player: $currentPlayer")

        Spacer(modifier = Modifier.height(16.dp))

        // 3x3 Grid for the Tic-Tac-Toe board
        for (i in 0..2) {
            Row {
                for (j in 0..2) {
                    val index = i * 3 + j
                    OutlinedButton(
                        onClick = { viewModel.makeMove(index) },
                        shape = RectangleShape,
                        modifier = Modifier
                            .size(100.dp)
                    ) {
                        Text(text = board[index], style = MaterialTheme.typography.headlineLarge)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Show winner message if there is one
        if (winner != null) {
            Text(text = "Winner: $winner", style = MaterialTheme.typography.titleMedium)
        }

        // Always show reset button
        Button(onClick = { viewModel.resetGame() }) {
            Text("Reset Game")
        }
    }
}
