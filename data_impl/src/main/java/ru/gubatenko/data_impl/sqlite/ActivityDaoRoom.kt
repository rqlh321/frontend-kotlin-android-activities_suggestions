package ru.gubatenko.data_impl.sqlite

import androidx.room.Dao
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.entity.ActivityStored

@Dao
interface ActivityDaoRoom : ActivityDao {

    override suspend fun save(activity: ActivityStored) {
        TODO("Not yet implemented")
    }

    override suspend fun activities(): List<ActivityStored> {
        TODO("Not yet implemented")
    }
}