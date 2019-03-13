package com.madisoft.raj.downloadtimecalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "history_table")
class History(@PrimaryKey @ColumnInfo(name = "history") val history: String)