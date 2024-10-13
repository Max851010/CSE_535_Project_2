package com.example.cse_535_project_2_jet.database
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Entity(tableName = "settings")
data class Settings (
    @PrimaryKey(autoGenerate = true) val settingsID: Int,
    val level: Char, // 0: Easy, 1: Medium, 2: Hard
    val type: Char // 0: local pvp, 1: pvc, 2: blue tooth pvp
)

@Entity(tableName = "histories")
@Parcelize
data class Histories (
    @PrimaryKey(autoGenerate = true) val historyID: Int = 0,
    val winner: String,
    val level: Char, // 0: Easy, 1: Medium, 2: Hard
    val date: LocalDateTime = LocalDateTime.now(),
) : Parcelable {
    val createdDateFormatted : String
        get() = date.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}
