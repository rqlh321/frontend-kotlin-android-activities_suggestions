package ru.gubatenko.domain_impl.mapper

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain_impl.data.entity.ActivityStored

class ActivityStoredToDomainMapper : Mapper<ActivityStored, Activity> {
    override fun convert(data: ActivityStored) = Activity(
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}