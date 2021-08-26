package ru.gubatenko.data_impl.sqlite

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ActivityStoredEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDaoRoom
}