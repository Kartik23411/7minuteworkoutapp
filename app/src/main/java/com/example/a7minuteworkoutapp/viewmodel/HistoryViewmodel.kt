package com.example.a7minuteworkoutapp.viewmodel

import android.icu.util.Calendar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a7minuteworkoutapp.database.Graph
import com.example.a7minuteworkoutapp.database.HistoryDate
import com.example.a7minuteworkoutapp.database.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class HistoryViewmodel(private val historyRepository: HistoryRepository = Graph.repository):ViewModel() {

    fun addToDatabase(){
        val myCalendar = Calendar.getInstance()
        val dateTime = myCalendar.time

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        viewModelScope.launch {
            historyRepository.addDate(HistoryDate(date))
        }
    }

    var getAllTodo: Flow<List<HistoryDate>> = historyRepository.getAllDates()

}