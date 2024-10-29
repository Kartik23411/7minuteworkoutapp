package com.example.a7minuteworkoutapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History-Table")
data class HistoryDate(
    @PrimaryKey
    val historyDate:String
)
