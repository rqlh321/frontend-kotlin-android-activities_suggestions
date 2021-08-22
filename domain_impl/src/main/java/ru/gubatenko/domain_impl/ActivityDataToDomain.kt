package ru.gubatenko.domain_impl

import ru.gubatenko.domain_impl.dto.ActivityDto
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper

class ActivityDataToDomain : Mapper<ActivityDto, Activity> {
    override fun convert(data: ActivityDto) = Activity(
        activity = data.activity,
        type = data.type,
        accessibility = data.accessibility,
    )
}