package com.example.a7minuteworkoutapp.database

import kotlinx.coroutines.flow.Flow

class HistoryRepository (private val historyDao: HistoryDao){

    suspend fun addDate(date: HistoryDate){
        historyDao.addDate(date)
    }
    fun getAllDates(): Flow<List<HistoryDate>> = historyDao.getAllTDates()

}