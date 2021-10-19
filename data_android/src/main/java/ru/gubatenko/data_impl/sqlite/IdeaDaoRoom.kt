package ru.gubatenko.data_impl.sqlite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.gubatenko.data.dao.IdeaDao

@Dao
interface IdeaDaoRoom : IdeaDao<IdeaStoredEntity> {

    @Query("DELETE FROM activity_table")
    override suspend fun delete()

    @Insert
    override suspend fun save(idea: List<IdeaStoredEntity>)

    @Insert
    override suspend fun save(idea: IdeaStoredEntity)

    @Query("SELECT * FROM activity_table WHERE is_synced==0")
    override suspend fun getNotSynced(): List<IdeaStoredEntity>

    @Query("UPDATE activity_table SET is_synced=1 WHERE uid IN (:ids)")
    override suspend fun updateAsSynced(ids: List<Long>)

    @Query("SELECT * FROM activity_table")
    override suspend fun all(): List<IdeaStoredEntity>

    @Query("SELECT * FROM activity_table")
    override fun subscribe(): Flow<List<IdeaStoredEntity>>
}