package com.example.cse_535_project_2_jet.ui.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {
    var board by mutableStateOf(List(9) { "" })
        private set
    var currentPlayer by mutableStateOf("X")
        private set
    var winner by mutableStateOf<String?>(null)
        private set

    private var _difficulty = mutableStateOf("Easy")
    var difficulty: String
        get() = _difficulty.value
        private set(value) {
            _difficulty.value = value
            // Update game logic based on difficulty if necessary
        }

    fun updateDifficulty(level: String) {
        difficulty = level
    }

    fun makeMove(index: Int) {
        if (board[index].isEmpty() && winner == null) {
            board = board.toMutableList().apply { this[index] = currentPlayer }
            if (checkWin()) {
                winner = currentPlayer
            } else if (board.all { it.isNotEmpty() }) {
                winner = "Draw"
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
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