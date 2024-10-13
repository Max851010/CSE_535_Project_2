package com.example.cse_535_project_2_jet.ui.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cse_535_project_2_jet.ui.viewModel.TicTacToeViewModel
import com.example.cse_535_project_2_jet.viewModels.DataBaseViewModel

class TicTacToeViewModelFactory(
    private val databaseViewModel: DataBaseViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicTacToeViewModel::class.java)) {
            return TicTacToeViewModel(databaseViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
