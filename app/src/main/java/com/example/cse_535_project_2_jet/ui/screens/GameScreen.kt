package com.example.cse_535_project_2_jet.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel

@Composable
fun GameScreen(
    navController: NavHostController,
    viewModel: TicTacToeViewModel = viewModel()
) {
    val board = viewModel.board
    val currentPlayer = viewModel.currentPlayer
    val winner = viewModel.winner
    val selectedMode = viewModel.gameMode

    // Select game mode
    var expandedMode by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Current Player: $currentPlayer")

        Spacer(modifier = Modifier.height(16.dp))

        // Game Mode Selector (PvP or PvAI)
        Box {
            TextField(
                value = selectedMode,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Game Mode") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        "Trailing icon for exposed dropdown menu",
                        Modifier.clickable { expandedMode = !expandedMode }
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            DropdownMenu(
                expanded = expandedMode,
                onDismissRequest = { expandedMode = false },
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.7f).dp)
            ) {
                DropdownMenuItem(
                    text = { Text("Player vs Player") },
                    onClick = {
                        viewModel.updateGameMode("PvP")
                        expandedMode = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Player vs AI") },
                    onClick = {
                        viewModel.updateGameMode("PvAI")
                        expandedMode = false
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Difficulty selector appears only if "PvAI" is selected
        if (selectedMode == "PvAI") {
            // Difficulty selector code remains the same as before
            DifficultySelector(viewModel)
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

@Composable
fun DifficultySelector(viewModel: TicTacToeViewModel) {
    val selectedDifficulty = viewModel.difficulty
    var expandedDifficulty by remember { mutableStateOf(false) }

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
                    Modifier.clickable { expandedDifficulty = !expandedDifficulty }
                )
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        DropdownMenu(
            expanded = expandedDifficulty,
            onDismissRequest = { expandedDifficulty = false },
            modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.7f).dp)
        ) {
            DropdownMenuItem(
                text = { Text("Easy") },
                onClick = {
                    viewModel.updateDifficulty("Easy")
                    expandedDifficulty = false
                }
            )
            DropdownMenuItem(
                text = { Text("Medium") },
                onClick = {
                    viewModel.updateDifficulty("Medium")
                    expandedDifficulty = false
                }
            )
            DropdownMenuItem(
                text = { Text("Hard") },
                onClick = {
                    viewModel.updateDifficulty("Hard")
                    expandedDifficulty = false
                }
            )
        }
    }
}

