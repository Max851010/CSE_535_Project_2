package com.example.cse_535_project_2_jet.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cse_535_project_2_jet.database.GameDatabase
import com.example.cse_535_project_2_jet.navigation.AppNavHost
import com.google.androidgamesdk.gametextinput.Settings

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val selectedIndex = remember { mutableStateOf(0) }
    lateinit var gameDatabase: GameDatabase

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                selectedIndex = selectedIndex.value) { index ->
                    selectedIndex.value = index
                }
            }
    ) {
        AppNavHost(navController = navController)
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit) {
    NavigationBar {
        // Define your navigation items
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Past Game Screen") },
            label = { Text("History") },
            selected = selectedIndex == 0,
            onClick = {
                navController.navigate("past_game")
                onItemSelected(0)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Game Screen") },
            label = { Text("Home") },
            selected = selectedIndex == 1,
            onClick = {
                navController.navigate("game")
                onItemSelected(1)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Settings, contentDescription = "Settings") },
            label = { Text("Settings") },
            selected = selectedIndex == 2,
            onClick = {
                navController.navigate("settings")
                onItemSelected(2)
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color.Blue,
                unselectedIconColor = Color.Gray,
                selectedTextColor = Color.Blue,
                unselectedTextColor = Color.Gray
            )
        )
    }
}
