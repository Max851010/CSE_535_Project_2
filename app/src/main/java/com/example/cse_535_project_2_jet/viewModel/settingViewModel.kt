package com.example.cse_535_project_2_jet.viewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Setting: ViewModel() {

    var difficultyLevel by mutableStateOf("")


    fun onButton1Click() {
        difficultyLevel = "Easy"
    }

    fun onButton2Click() {
        difficultyLevel = "Medium"
    }

    fun onButton3Click() {
        difficultyLevel = "Hard"
    }


}