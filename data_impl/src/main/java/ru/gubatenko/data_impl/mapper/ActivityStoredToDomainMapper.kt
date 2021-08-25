package ru.gubatenko.data_impl.mapper

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.data.entity.ActivityStored

class ActivityStoredToDomainMapper : Mapper<ActivityStored, Activity> {
    override fun convert(data: ActivityStored) = Activity(
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}