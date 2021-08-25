package ru.gubatenko.data_impl

import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.entity.ActivityStored

class ActionDaoSharedPrefImpl : ActivityDao {

    private val list = ArrayList<ActivityStored>()

    override suspend fun save(activity: ActivityStored) {
        list.add(activity)
    }

    override suspend fun activities() = list
}