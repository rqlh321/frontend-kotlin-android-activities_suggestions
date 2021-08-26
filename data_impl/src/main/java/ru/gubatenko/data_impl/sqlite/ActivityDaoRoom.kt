package ru.gubatenko.data_impl.sqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.gubatenko.data.dao.ActivityDao

@Dao
interface ActivityDaoRoom : ActivityDao<ActivityStoredEntity> {

    @Insert
    override suspend fun save(activity: ActivityStoredEntity)

    @Query("SELECT * FROM activity_table")
    override suspend fun activities(): List<ActivityStoredEntity>
}