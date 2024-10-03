package com.example.cse_535_project_2_jet.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao{
    @Insert
    suspend fun insertRecord(histories: Histories)

    @Query("SELECT * FROM histories;")
    suspend fun getHistories(): List<Histories>
}