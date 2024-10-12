package com.example.cse_535_project_2_jet.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cse_535_project_2_jet.components.DateConverters
//import com.example.cse_535_project_2_jet.database.migrations.MyAutoMigrationSpec

@Database(
    entities = [Settings::class, Histories::class], version = 1,
    exportSchema = true
    )
@TypeConverters(DateConverters::class)
abstract class GameDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
    abstract fun historyDao(): HistoryDao

    // companion object: like static in Java (get memory while the program is loaded)
    // The companion object enforces a singleton pattern, meaning there will only be one instance of the database created throughout the application lifecycle.
    // This is particularly useful to avoid multiple instances of the database that can lead to data inconsistency and increased memory usage.
    companion object {
        // ensures that any write to the INSTANCE variable is immediately visible to all threads.
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
