package ru.gubatenko.patterns.storage

import ru.gubatenko.domain_impl.data.dao.ActivityDao
import ru.gubatenko.domain_impl.data.entity.ActivityStored

class ActionDaoSharedPrefImpl : ActivityDao {

    private val list = ArrayList<ActivityStored>()

    override suspend fun save(activity: ActivityStored) {
        list.add(activity)
    }

    override suspend fun activities() = list
}