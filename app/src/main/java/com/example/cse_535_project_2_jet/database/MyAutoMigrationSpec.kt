package com.example.cse_535_project_2_jet.database.migrations

import androidx.room.migration.AutoMigrationSpec
import androidx.room.RenameColumn

@RenameColumn.Entries(
    RenameColumn(
        tableName = "histories",
        fromColumnName = "player_name",
        toColumnName = "winner" // Replace with the new column name
    )
)
class MyAutoMigration : AutoMigrationSpec
