package com.example.a7minuteworkoutapp.database

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: HistoryDatabase

    val repository by lazy {
        HistoryRepository(historyDao = database.historyDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, HistoryDatabase::class.java, "history.db").build()
    }
}