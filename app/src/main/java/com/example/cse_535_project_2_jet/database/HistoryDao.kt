package com.example.cse_535_project_2_jet.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE) // REPLACE IF EXIST
    suspend fun insertOrUpdate(settings: Settings)

    @Query("SELECT * FROM histories;")
    suspend fun getHisory(id: Int): List<Settings>?
}