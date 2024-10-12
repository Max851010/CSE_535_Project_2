package com.example.cse_535_project_2_jet.ui.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.cse_535_project_2_jet.database.Histories
import com.example.cse_535_project_2_jet.viewModels.DataBaseViewModel

class TicTacToeViewModel(val dataBaseViewModel: DataBaseViewModel) : ViewModel() {
    var board by mutableStateOf(List(9) { "" })
        private set
    var currentPlayer by mutableStateOf("X")
        private set
    var currentDifficulty by mutableStateOf("Easy")
        private set
    var winner by mutableStateOf<String?>(null)
        private set

    private var _difficultyChar = mutableStateOf('0') // Store difficulty as a char
    var difficultyChar: Char
        get() = _difficultyChar.value
        private set(value) {
            _difficultyChar.value = value
            currentDifficulty = when (value) {
                '0' -> "Easy"
                '1' -> "Medium"
                '2' -> "Hard"
                else -> "Easy" // Default value
            }
        }

    private var _playerType = mutableStateOf("Single Player")
    var playerType: String
        get() = _playerType.value
        private set(value) {
            _playerType.value = value
            // You can add logic here if needed based on player type
        }

    private var _playerTypeChar = mutableStateOf('0') // '0' for "Vs Player"
    var playerTypeChar: Char
        get() = _playerTypeChar.value
        private set(value) {
            _playerTypeChar.value = value
        }

    fun updatePlayerType(type: String) {
        playerType = type
        playerTypeChar = when (type) {
            "Vs Player" -> '0'
            "Vs Computer" -> '1'
            else -> '0' // Default value
        }
    }


    fun updateDifficulty(level: Char) {
        difficultyChar = level
    }


    fun makeMove(index: Int) {
        if (board[index].isEmpty() && winner == null) {
            board = board.toMutableList().apply { this[index] = currentPlayer }
            // Connect to DB with winner, difficulty and Date time
            if (checkWin()) {
                winner = currentPlayer
            } else if (board.all { it.isNotEmpty() }) {
                winner = "Draw"
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
        val tmp_level: Char = '1'
        if (winner != null) {
            val new_history = Histories(
                winner = winner!!,
                level = tmp_level
            )
            dataBaseViewModel.insertHistory(new_history)
        }
    }

    private fun checkWin(): Boolean {
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // columns
            listOf(0, 4, 8), listOf(2, 4, 6)  // diagonals
        )
        return winPatterns.any { pattern ->
            pattern.all { board[it] == currentPlayer }
        }
    }

    fun resetGame() {
        board = List(9) { "" }
        currentPlayer = "X"
        winner = null
    }
}