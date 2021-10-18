package ru.gubatenko.data_impl.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.data_impl.sqlite.ActivityStoredEntity
import ru.gubatenko.domain.model.Activity

class ActivityFromDomainToStoredRoom : Mapper<Activity, ActivityStored>() {
    override fun map(data: Activity) = ActivityStoredEntity(
        activity = data.activity,
    )
}