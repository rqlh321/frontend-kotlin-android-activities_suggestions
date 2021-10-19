package ru.gubatenko.data_impl.sqlite

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [IdeaStoredEntity::class],
    version = 2,
    autoMigrations = [AutoMigration(from = 1, to = 2)],
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ideaDao(): IdeaDaoRoom
}