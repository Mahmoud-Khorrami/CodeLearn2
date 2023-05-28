package com.mahapp1397.codelearn2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahapp1397.codelearn2.models.Profile

@Database(entities = [Profile::class], version = 1, exportSchema = false)
abstract class ColdLearn2Database: RoomDatabase() {

    abstract fun myDao(): MyDao
}