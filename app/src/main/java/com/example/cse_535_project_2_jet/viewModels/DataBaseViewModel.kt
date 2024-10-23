package com.example.cse_535_project_2_jet.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cse_535_project_2_jet.database.GameDatabase
import com.example.cse_535_project_2_jet.database.Histories
import com.example.cse_535_project_2_jet.database.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class DataBaseViewModel(application: Application) : AndroidViewModel(application) {
    private val gameDatabase: GameDatabase by lazy {
        GameDatabase.getDatabase(application)
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    var setting: Settings? = null
        private set

    var histories: Flow<List<Histories>> = flowOf(emptyList())
        private set


    fun insertOrUpdateSetting(level: Char, type: Char) {
        val newSetting = Settings(settingsID = 1, level = level, type = type)
        viewModelScope.launch {
            gameDatabase.settingsDao().upsert(newSetting)
        }
    }

    fun loadSettings() {
        viewModelScope.launch {
            // Indicate that loading is in progress
            _isLoading.value = true
            try {
                setting = gameDatabase.settingsDao().getSettingById(1)
                Log.d("DataBaseViewModel", "Loading settings")

                if (setting == null) {
                    Log.d("DataBaseViewModel", "Initialize the first setting record")
                    insertOrUpdateSetting('0', '1')
                    setting = gameDatabase.settingsDao().getSettingById(1)
                    Log.d("DataBaseViewModel", "Loading settings after initialization")
                }
            } finally {
                // Once loading is done, update the loading state
                if (setting != null) {
                    _isLoading.value = false
                }
            }
        }
    }

    fun insertHistory(history: Histories) {
        viewModelScope.launch {
            gameDatabase.historyDao().insertRecord(history)
            Log.d("DataBaseViewModel", "History inserted")
        }
    }

    fun loadHistories() {
        viewModelScope.launch {
            histories = gameDatabase.historyDao().getHistories()
            Log.d("DataBaseViewModel", "Histories Loaded")
        }
    }
}
