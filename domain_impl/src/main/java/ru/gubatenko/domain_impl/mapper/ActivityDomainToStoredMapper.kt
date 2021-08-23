package ru.gubatenko.domain_impl.mapper

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain_impl.data.entity.ActivityStored

class ActivityDomainToStoredMapper : Mapper<Activity, ActivityStored> {
    override fun convert(data: Activity) = ActivityStored(
        activity = data.activity,
    )
}