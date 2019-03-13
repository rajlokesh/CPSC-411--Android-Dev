package com.madisoft.raj.downloadtimecalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.launch

@Database(entities = [History::class], version = 1)
 abstract class HistoryRoomDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryRoomDatabase? = null

        fun getDatabase(context: Context ,
                        scope: CoroutineScope): HistoryRoomDatabase {
            val tempInstance     = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryRoomDatabase::class.java,
                        "History_database"
                ).addCallback(HistoryDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }


        private class HistoryDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.historyDao())
                    }
                }
            }
        }
        fun clearDB(historyDao: HistoryDao)
        {
            historyDao.deleteAll()
        }
        fun populateDatabase(historyDao: HistoryDao) {
/*

            var word = History("Hello")
            historyDao.insert(word)*/


        }
    }

}
