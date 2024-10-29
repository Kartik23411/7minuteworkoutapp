package com.example.a7minuteworkoutapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addDate(historyEntity: HistoryDate)

    @Query("select * from `History-Table`")
    abstract fun getAllTDates(): Flow<List<HistoryDate>>

//    @Update
//    abstract suspend fun updateDate(todoEntity: HistoryDate)
//
//    @Delete
//    abstract suspend fun deleteDate(todoEntity: HistoryDate)
}