package com.madisoft.raj.downloadtimecalculator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * from history_table ORDER BY history DESC")
    fun getAllHistory(): LiveData<List<History>>

    @Insert
    fun insert(history: History)

    @Query("DELETE FROM history_table")
    fun deleteAll()

}