package com.example.cse_535_project_2_jet.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao{
    @Insert
    suspend fun insertRecord(histories: Histories)

    @Query("SELECT * FROM histories ORDER BY date DESC;")
    fun getHistories(): Flow<List<Histories>>
}