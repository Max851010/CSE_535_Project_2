package com.example.cse_535_project_2_jet.database
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date


@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey(autoGenerate = true) val settingsID: Int,
    val level: Char // 0: Easy, 1: Medium, 2: Hard
)

@Entity(tableName = "histories")
@Parcelize
data class Histories (
    @PrimaryKey(autoGenerate = true) val historyID: Int,
    val level: Char, // 0: Easy, 1: Medium, 2: Hard
    val date: LocalDateTime = LocalDateTime.now(),
) : Parcelable {
    val createdDateFormatted : String
        get() = date.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
