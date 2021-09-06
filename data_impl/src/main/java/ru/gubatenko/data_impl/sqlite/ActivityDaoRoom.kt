package ru.gubatenko.data_impl.sqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.gubatenko.data.dao.ActivityDao

@Dao
interface ActivityDaoRoom : ActivityDao<ActivityStoredEntity> {

    @Insert
    override suspend fun saveAll(activity: List<ActivityStoredEntity>)

    @Insert
    override suspend fun save(activity: ActivityStoredEntity)

    @Query("SELECT * FROM activity_table WHERE is_synced==0")
    override suspend fun getNotSynced(): List<ActivityStoredEntity>

    @Query("UPDATE activity_table SET is_synced=1 WHERE uid IN (:ids)")
    override suspend fun updateAsSynced(ids: List<Long>)

    @Query("SELECT * FROM activity_table")
    override suspend fun all(): List<ActivityStoredEntity>

    @Query("SELECT * FROM activity_table")
    override fun subscribe(): Flow<List<ActivityStoredEntity>>
}