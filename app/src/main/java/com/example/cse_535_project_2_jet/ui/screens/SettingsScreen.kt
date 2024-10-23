package com.example.cse_535_project_2_jet.ui.screens
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.cse_535_project_2_jet.ui.components.TicTacToeViewModelFactory
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel
import com.example.cse_535_project_2_jet.viewModels.DataBaseViewModel


@Composable
fun SettingsScreen(
    navController: NavHostController,
    databaseViewModel: DataBaseViewModel = viewModel() // Default ViewModel provider
) {
    val viewModel: TicTacToeViewModel = viewModel(
        factory = TicTacToeViewModelFactory(databaseViewModel)
    )
    val playerTypeMap = mapOf(
        '0' to "Vs Player",
        '1' to "Vs Computer",
    )

    LaunchedEffect(Unit) {
        databaseViewModel.loadSettings()
        val settingsObj = databaseViewModel.setting
        if (settingsObj != null) {
            viewModel.updateDifficulty(settingsObj.level)
            playerTypeMap[settingsObj.type]?.let { viewModel.updatePlayerType(it) }
        } else {
            // Set default to "Vs Computer"
            viewModel.updateDifficulty('0')
            viewModel.updatePlayerType("Vs Computer")
        }
    }

    var expandedDifficulty by remember { mutableStateOf(false) }
    var expandedPlayerType by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Player type selection
        Text(text = "Current Player Type: ${viewModel.playerType}")
        Spacer(modifier = Modifier.height(5.dp))

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
                        Modifier.clickable { expandedPlayerType = !expandedPlayerType }
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

        Spacer(modifier = Modifier.height(16.dp))

        // Difficulty level selection (visible only when Vs Computer is selected)
        if (viewModel.playerType == "Vs Computer") {
            Text(text = "Current Difficulty: ${viewModel.currentDifficulty}")
            Spacer(modifier = Modifier.height(5.dp))

            Box {
                TextField(
                    value = viewModel.currentDifficulty,
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
                            viewModel.updateDifficulty('0')
                            expandedDifficulty = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Medium") },
                        onClick = {
                            viewModel.updateDifficulty('1')
                            expandedDifficulty = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Hard") },
                        onClick = {
                            viewModel.updateDifficulty('2')
                            expandedDifficulty = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // Update Button
        Button(onClick = {
            databaseViewModel.insertOrUpdateSetting(level = viewModel.difficultyChar, type = viewModel.playerTypeChar)
            databaseViewModel.loadSettings()
            Log.d("SettingsScreen", "Difficulty Level: ${viewModel.difficultyChar}") // Log the reset action
            Log.d("SettingsScreen", "Player Type: ${viewModel.playerTypeChar}") // Log the reset action
        }) {
            Text("Update")
        }
    }
}
