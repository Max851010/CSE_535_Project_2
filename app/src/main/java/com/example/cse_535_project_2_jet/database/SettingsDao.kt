package com.example.cse_535_project_2_jet.database
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SettingsDao{
    @Upsert
    suspend fun upsert(settings: Settings)

    @Query("SELECT * FROM settings WHERE settingsID = :id LIMIT 1")
    suspend fun getSettingById(id: Int): Settings?
}