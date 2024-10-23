package com.example.cse_535_project_2_jet.ui.viewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cse_535_project_2_jet.database.Histories
import com.example.cse_535_project_2_jet.viewModels.DataBaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
            if (checkWin()) {
                winner = currentPlayer
            } else if (board.all { it.isNotEmpty() }) {
                winner = "Draw"
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                //if (playerTypeChar == '1' && currentPlayer == "O") {
                //    aiMove() // Call AI move function if it's AI's turn
                //}
                Log.d("player type", "${dataBaseViewModel.setting?.type}")
                if(dataBaseViewModel.setting?.type == '1' && currentPlayer == "O") {
                    Log.d("Difficulty: ", "Difficulty: ${dataBaseViewModel.setting?.level}")
                    aiMove()
                }
            }
        }

//        if (winner != null) {
//            if (winner != "Draw") {
//                winner = if (winner == "O") "Player 2" else "Player 1"
//            }
//            Log.d("db", "$winner")
//            Log.d("View Model", "Difficulty Level: ${difficultyChar}") // Log the reset action
//            Log.d("View Model", "Player Type: ${playerTypeChar}") // Log the reset action
//            val newHistory = Histories(winner = winner!!, level = difficultyChar)
//            dataBaseViewModel.insertHistory(newHistory)
//        }
        // Store winner in the database if there's a result
        if (winner != null) {
            // Determine if it's a player vs AI game and the AI won
            val isVsAI = dataBaseViewModel.setting?.type == '1'
            val aiWins = isVsAI && winner == "O"
            val humanWins = isVsAI && winner == "X"

            // Update winner based on the game mode
            if (winner != "Draw") {
                if (aiWins) {
                    winner = "AI"
                } else if (humanWins) {
                    winner = "Player"
                } else {
                    winner = if (winner == "O") "Player 2" else "Player 1"
                }
            }

            Log.d("db", "$winner")
            Log.d("View Model", "Difficulty Level: $difficultyChar")
            Log.d("View Model", "Player Type: $playerTypeChar")

            // Insert result into database
            val newHistory = Histories(
                winner = winner!!,
                level = difficultyChar
            )
            dataBaseViewModel.insertHistory(newHistory)
        }
    }

    // AI move logic based on difficulty
    private fun aiMove() {
        viewModelScope.launch {
            delay(800) // Add a 500ms delay before the AI makes its move
            val bestMove = when (dataBaseViewModel.setting?.level) {
                '0' -> getRandomMove() // Easy
                '1' -> if (Math.random() > 0.5) getRandomMove() else minimaxMove() // Medium
                '2' -> minimaxMove() // Hard
                else -> getRandomMove() // Default easy
            }
            makeMove(bestMove)
        }
    }

    // Random move for 'Easy' difficulty
    private fun getRandomMove(): Int {
        val availableMoves = board.indices.filter { board[it].isEmpty() }
        return availableMoves.random()
    }

    // Minimax algorithm implementation
    private fun minimax(board: List<String>, isMaximizing: Boolean): Int {
        val winner = checkWinnerForMinimax(board)
        if (winner != null) {
            return when (winner) {
                "X" -> -10
                "O" -> 10
                "Draw" -> 0
                else -> 0
            }
        }

        val availableMoves = board.indices.filter { board[it].isEmpty() }
        if (isMaximizing) {
            var bestScore = Int.MIN_VALUE
            for (move in availableMoves) {
                val newBoard = board.toMutableList().apply { this[move] = "O" }
                val score = minimax(newBoard, false)
                bestScore = maxOf(bestScore, score)
            }
            return bestScore
        } else {
            var bestScore = Int.MAX_VALUE
            for (move in availableMoves) {
                val newBoard = board.toMutableList().apply { this[move] = "X" }
                val score = minimax(newBoard, true)
                bestScore = minOf(bestScore, score)
            }
            return bestScore
        }
    }

    // Get the best move for 'Hard' difficulty using the minimax algorithm
    private fun minimaxMove(): Int {
        var bestScore = Int.MIN_VALUE
        var bestMove = -1
        val availableMoves = board.indices.filter { board[it].isEmpty() }
        for (move in availableMoves) {
            val newBoard = board.toMutableList().apply { this[move] = "O" }
            val score = minimax(newBoard, false)
            if (score > bestScore) {
                bestScore = score
                bestMove = move
            }
        }
        return bestMove
    }

    // Helper function to check the winner for the minimax algorithm
    private fun checkWinnerForMinimax(board: List<String>): String? {
        val winPatterns = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // columns
            listOf(0, 4, 8), listOf(2, 4, 6)  // diagonals
        )

        for (pattern in winPatterns) {
            val (a, b, c) = pattern
            if (board[a] == board[b] && board[b] == board[c] && board[a].isNotEmpty()) {
                return board[a]
            }
        }

        return if (board.all { it.isNotEmpty() }) "Draw" else null
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