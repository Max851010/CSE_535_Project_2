package com.example.cse_535_project_2_jet.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.cse_535_project_2_jet.viewModels.DataBaseViewModel
import kotlinx.coroutines.flow.forEach
import java.time.format.DateTimeFormatter


@Composable
fun PastGameScreen(
    navController: NavHostController,
    databaseViewModel: DataBaseViewModel = viewModel()// Default ViewModel provider
    ) {

    LaunchedEffect(Unit) {
        databaseViewModel.loadHistories()
    }
    val histories by databaseViewModel.histories.collectAsState(initial = emptyList())
    var dates by remember { mutableStateOf(listOf<String>()) }
    var difficultyModes by remember { mutableStateOf(listOf<String>()) }
    var winners by remember { mutableStateOf(listOf<String>()) }
    LaunchedEffect(histories) {
        Log.d("PastGameScreen", "Histories Size: ${histories.size}")
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        dates = histories.map { it.date.format(dateFormatter) + "  " + it.date.format(timeFormatter)}
        winners = histories.map { it.winner }
        difficultyModes = histories.map {
            when (it.level) {
                '0' -> "Easy"
                '1' -> "Medium"
                '2' -> "Hard"
                else -> "Unknown"
            }
        }
    }

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
        DateHeaderCell("Time")
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
        DateDataCell(date)
        DataCell(winner)
        DataCell(difficulty)
    }
}
@Composable
fun DateDataCell(text: String) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(180.dp, 50.dp)
        ,// Keep the same size for consistency
        contentAlignment = Alignment.Center
    ) {
        Text(text=text)
    }
}
@Composable
fun DataCell(text: String) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(80.dp, 50.dp)
           ,// Keep the same size for consistency
        contentAlignment = Alignment.Center
    ) {
        Text(text=text)
    }
}
@Composable
fun DateHeaderCell(text: String) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(180.dp, 50.dp)
        ,// Keep the same size for consistency
        contentAlignment = Alignment.Center
    ) {
        Text(text=text, fontWeight = FontWeight.Bold)
    }
}
@Composable
fun HeaderCell(text: String) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .size(80.dp, 50.dp)
        ,// Keep the same size for consistency
        contentAlignment = Alignment.Center
    ) {
        Text(text=text, fontWeight = FontWeight.Bold)
    }
}
