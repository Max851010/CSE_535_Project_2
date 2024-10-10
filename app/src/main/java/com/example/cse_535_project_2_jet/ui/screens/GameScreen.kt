package com.example.cse_535_project_2_jet.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.database.GameDatabase

import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel

@Composable
fun GameScreen(
    navController: NavHostController,
    viewModel: TicTacToeViewModel = viewModel() // Default ViewModel provider
)  {
    lateinit var gameDatabase: GameDatabase
    val board = viewModel.board
    val currentPlayer = viewModel.currentPlayer
    val winner = viewModel.winner

    // Select difficulty
    val selectedDifficulty = viewModel.difficulty
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Player: $currentPlayer")

        Spacer(modifier = Modifier.height(16.dp))

        Box {
            TextField(
                value = selectedDifficulty,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Difficulty") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        "Trailing icon for exposed dropdown menu",
                        Modifier.clickable { expanded = !expanded } // Toggle expanded state here
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.7f).dp)
            ) {
                DropdownMenuItem(
                    text = { Text("Easy") },
                    onClick = {
                        viewModel.updateDifficulty("Easy")
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Medium") },
                    onClick = {
                        viewModel.updateDifficulty("Medium")
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Hard") },
                    onClick = {
                        viewModel.updateDifficulty("Hard")
                        expanded = false
                    }
                )
            }
        }

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
            Text(text = "Winner: $winner", style = MaterialTheme.typography.titleMedium)
        }

        // Always show reset button
        Button(onClick = { viewModel.resetGame() }) {
            Text("Reset Game")
        }
    }
}
