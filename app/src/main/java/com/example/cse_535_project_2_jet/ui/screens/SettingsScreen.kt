package com.example.cse_535_project_2_jet.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.database.GameDatabase

@Composable
fun SettingsScreen(navController: NavHostController) {
    lateinit var gameDatabase: GameDatabase
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is the Settings Screen")
    }
}
