package ru.gubatenko.data_impl.mapper

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.data.entity.ActivityStored

class ActivityDomainToStoredMapper : Mapper<Activity, ActivityStored> {
    override fun convert(data: Activity) = ActivityStored(
        activity = data.activity,
    )
}