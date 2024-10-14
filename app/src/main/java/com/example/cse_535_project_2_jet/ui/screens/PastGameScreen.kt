package com.example.cse_535_project_2_jet.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun PastGameScreen(navController: NavHostController) {
    // Sample data
    val dates = listOf("2024-01-01", "2024-01-02", "2024-01-03")
    val winners = listOf("Human", "Computer", "Human")
    val difficultyModes = listOf("Hard", "Medium", "Easy")

    // LazyColumn to create a vertical list
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Header Row
        item {
            HeaderRow()
        }

        // Data Rows
        for (i in dates.indices) {
            item {
                DataRow(dates[i], winners[i], difficultyModes[i])
            }
        }
    }
}

@Composable
fun HeaderRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top=50.dp)
            .border(1.dp, Color.Black)
            .background(Color(0xFFB0C4DE)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        HeaderCell("Date")
        HeaderCell("Winner")
        HeaderCell("Difficulty")
    }
}

@Composable
fun DataRow(date: String, winner: String, difficulty: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp)
            .border(1.dp, Color.Black),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        DataCell(date)
        DataCell(winner)
        DataCell(difficulty)
    }
}

@Composable
fun DataCell(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp, 50.dp)
           ,// Keep the same size for consistency
        contentAlignment = Alignment.Center
    ) {
        Text(text=text)
    }
}
@Composable
fun HeaderCell(text: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp, 50.dp)
        ,// Keep the same size for consistency
        contentAlignment = Alignment.Center
    ) {
        Text(text=text, fontWeight = FontWeight.Bold)
    }
}
