package com.madisoft.raj.downloadtimecalculator

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class HistoryRepository(private val historyDao: HistoryDao) {

    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()

    @WorkerThread
    suspend fun insert(history: History) {
        historyDao.insert(history)
    }

    @WorkerThread
    suspend fun delete() {
        historyDao.deleteAll()
    }


}