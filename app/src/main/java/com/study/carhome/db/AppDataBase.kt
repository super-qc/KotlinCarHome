package com.study.carhome.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.study.carhome.entity.CarBrandEntity


@Database(
    entities = [CarBrandEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun carBrandDao():CarBrandDao
}