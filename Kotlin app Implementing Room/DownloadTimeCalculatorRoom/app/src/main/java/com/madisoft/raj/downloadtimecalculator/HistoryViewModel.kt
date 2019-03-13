package com.madisoft.raj.downloadtimecalculator


import android.app.Application

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData



 class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: HistoryRepository
    val allHistorys: LiveData<List<History>>

    init {
        val historysDao = HistoryRoomDatabase.getDatabase(application,scope).historyDao()
        repository = HistoryRepository(historysDao)
        allHistorys = repository.allHistory
    }

    fun insert(history: History) = scope.launch(Dispatchers.IO) {
        repository.insert(history)
    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

     fun deleteAll() = scope.launch(Dispatchers.IO)  {
         repository.delete()
     }
 }