package ru.gubatenko.repo_impl

import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.domain.model.Activity

class ActivityDataToDomain : Mapper<ActivityDto, Activity> {
    override fun convert(data: ActivityDto) = Activity(
        activity = data.activity,
        type = data.type,
        accessibility = data.accessibility,
    )
}