package com.example.cse_535_project_2_jet.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.database.GameDatabase
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel


@Composable
fun SettingsScreen(navController: NavHostController,
                   viewModel: TicTacToeViewModel = viewModel()) {
    lateinit var gameDatabase: GameDatabase

    val currentDifficulty = viewModel.currentDifficulty
    val selectedDifficulty = viewModel.difficulty
    var expandedDifficulty by remember { mutableStateOf(false) }
    var expandedPlayerType by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Current Difficulty: $currentDifficulty")
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
                        Modifier.clickable { expandedDifficulty = !expandedDifficulty } // Toggle expanded state for difficulty
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            DropdownMenu(
                expanded = expandedDifficulty,
                onDismissRequest = { expandedDifficulty  = false },
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.7f).dp)
            ) {
                DropdownMenuItem(
                    text = { Text("Easy") },
                    onClick = {
                        viewModel.updateDifficulty("Easy")
                        expandedDifficulty  = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Medium") },
                    onClick = {
                        viewModel.updateDifficulty("Medium")
                        expandedDifficulty  = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Hard") },
                    onClick = {
                        viewModel.updateDifficulty("Hard")
                        expandedDifficulty  = false
                    }
                )
            }
        }



        Text(text = "Current Player Type: ${viewModel.playerType}")
        Box {
            TextField(
                value = viewModel.playerType,
                onValueChange = { },
                readOnly = true,
                label = { Text("Select Player Type") },
                trailingIcon = {
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        "Trailing icon for player type dropdown menu",
                        Modifier.clickable { expandedPlayerType = !expandedPlayerType } // Toggle expanded state for player type
                    )
                },
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            DropdownMenu(
                expanded = expandedPlayerType,
                onDismissRequest = { expandedPlayerType = false },
                modifier = Modifier.width((LocalConfiguration.current.screenWidthDp * 0.7f).dp)
            ) {
                DropdownMenuItem(
                    text = { Text("Vs Computer") },
                    onClick = {
                        viewModel.updatePlayerType("Vs Computer")
                        expandedPlayerType = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Vs Player") },
                    onClick = {
                        viewModel.updatePlayerType("Vs Player")
                        expandedPlayerType = false
                    }
                )
            }
        }



    }
}
