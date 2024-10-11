package com.example.cse_535_project_2_jet.ui.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TicTacToeViewModel : ViewModel() {
    var board by mutableStateOf(List(9) { "" })
        private set
    var currentPlayer by mutableStateOf("X")
        private set
    var winner by mutableStateOf<String?>(null)
        private set

    // New state variable for game mode
    private var _gameMode = mutableStateOf("PvP") // Default to PvP (Person vs Person)
    var gameMode: String
        get() = _gameMode.value
        private set(value) {
            _gameMode.value = value
        }

    private var _difficulty = mutableStateOf("Easy") // Default difficulty for AI
    var difficulty: String
        get() = _difficulty.value
        private set(value) {
            _difficulty.value = value
        }

    // Update game mode (PvP or PvAI)
    fun updateGameMode(mode: String) {
        gameMode = mode
    }

    // Update difficulty level for AI
    fun updateDifficulty(level: String) {
        difficulty = level
    }

    fun makeMove(index: Int) {
        if (board[index].isEmpty() && winner == null) {
            board = board.toMutableList().apply { this[index] = currentPlayer }

            // First, check if the game is a draw (no more empty cells)
            if (board.all { it.isNotEmpty() }) {
                winner = if (checkWin()) currentPlayer else "Draw"
            }
            // Then, check if the current player has won
            else if (checkWin()) {
                winner = currentPlayer
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"

                // If gameMode is PvAI and it's AI's turn, delay the AI move
                if (gameMode == "PvAI" && currentPlayer == "O" && winner == null) {
                    viewModelScope.launch {
                        delay(500) // 0.5 second delay
                        makeAIMove()
                    }
                }
            }
        }
    }

    private fun makeAIMove() {
        when (difficulty) {
            "Easy" -> makeRandomAIMove()
            "Medium" -> if ((0..1).random() == 0) makeRandomAIMove() else makeBestMoveUsingMinimax()
            "Hard" -> makeBestMoveUsingMinimax()
        }
    }

    private fun makeRandomAIMove() {
        val emptyIndices = board.mapIndexedNotNull { index, value -> if (value.isEmpty()) index else null }
        if (emptyIndices.isNotEmpty()) {
            val randomIndex = emptyIndices.random()
            board = board.toMutableList().apply { this[randomIndex] = "O" }
            if (checkWin()) {
                winner = "O"
            } else {
                currentPlayer = "X"
            }
        }
    }

    private fun makeBestMoveUsingMinimax() {
        var bestScore = Int.MIN_VALUE
        var bestMove = -1
        for (i in board.indices) {
            if (board[i].isEmpty()) {
                val newBoard = board.toMutableList()
                newBoard[i] = "O"
                val score = minimax(newBoard, 0, false)
                if (score > bestScore) {
                    bestScore = score
                    bestMove = i
                }
            }
        }
        if (bestMove != -1) {
            board = board.toMutableList().apply { this[bestMove] = "O" }
            if (checkWin()) {
                winner = "O"
            } else {
                currentPlayer = "X"
            }
        }
    }

    private fun minimax(board: List<String>, depth: Int, isMaximizing: Boolean): Int {
        val result = checkWinnerForMinimax(board)
        if (result != null) {
            return when (result) {
                "O" -> 10 - depth
                "X" -> depth - 10
                "Draw" -> 0
                else -> 0
            }
        }

        if (isMaximizing) {
            var bestScore = Int.MIN_VALUE
            for (i in board.indices) {
                if (board[i].isEmpty()) {
                    val newBoard = board.toMutableList() // Create a mutable copy of the board
                    newBoard[i] = "O"
                    val score = minimax(newBoard, depth + 1, false)
                    bestScore = maxOf(bestScore, score)
                }
            }
            return bestScore
        } else {
            var bestScore = Int.MAX_VALUE
            for (i in board.indices) {
                if (board[i].isEmpty()) {
                    val newBoard = board.toMutableList() // Create a mutable copy of the board
                    newBoard[i] = "X"
                    val score = minimax(newBoard, depth + 1, true)
                    bestScore = minOf(bestScore, score)
                }
            }
            return bestScore
        }
    }

    private fun checkWinnerForMinimax(board: List<String>): String? {
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // columns
            listOf(0, 4, 8), listOf(2, 4, 6)  // diagonals
        )

        for (pattern in winPatterns) {
            val (a, b, c) = pattern
            if (board[a] == board[b] && board[b] == board[c] && board[a].isNotEmpty()) {
                return board[a] // Return winner ("X" or "O")
            }
        }

        return if (board.all { it.isNotEmpty() }) "Draw" else null
    }

//    private fun checkWin(): Boolean {
//        val result = checkWinnerForMinimax(board)
//        if (result != null) {
//            winner = result
//            return true
//        }
//        return false
//    }


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