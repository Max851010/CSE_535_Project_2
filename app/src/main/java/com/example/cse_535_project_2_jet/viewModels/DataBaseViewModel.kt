package com.example.cse_535_project_2_jet.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cse_535_project_2_jet.database.GameDatabase
import com.example.cse_535_project_2_jet.database.Histories
import com.example.cse_535_project_2_jet.database.Settings
import kotlinx.coroutines.launch

class DataBaseViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var gameDatabase: GameDatabase
    fun getDatabase() {
        viewModelScope.launch {
            gameDatabase = GameDatabase.getDatabase(getApplication())
        }
    }
    var setting: Settings? = null
        private set

    var histories: List<Histories> = emptyList()
        private set

    fun insertOrUpdateSetting(level: Char, type: Char) {
        val newSetting = Settings(settingsID = 1, level = level, type = type)
        viewModelScope.launch {
            gameDatabase.settingsDao().upsert(newSetting)
        }
    }

    fun loadSettings() {
        viewModelScope.launch {
            setting = gameDatabase.settingsDao().getSettingById(1)
        }
    }

    fun insertHistory(history: Histories) {
        viewModelScope.launch {
            gameDatabase.historyDao().insertRecord(history)
        }
    }

    fun loadHistories() {
        viewModelScope.launch {
            histories = gameDatabase.historyDao().getHistories()
        }
    }
}
