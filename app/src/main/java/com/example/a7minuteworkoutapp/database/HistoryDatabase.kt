package com.example.a7minuteworkoutapp.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HistoryDate::class],
    exportSchema = false,
    version = 1
)
abstract class HistoryDatabase:RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
