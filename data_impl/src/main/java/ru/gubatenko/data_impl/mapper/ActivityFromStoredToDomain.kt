package ru.gubatenko.data_impl.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.domain.model.Activity

class ActivityFromStoredToDomain : Mapper<ActivityStored, Activity>() {
    override fun map(data: ActivityStored) = Activity(
        uid = data.uid,
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}