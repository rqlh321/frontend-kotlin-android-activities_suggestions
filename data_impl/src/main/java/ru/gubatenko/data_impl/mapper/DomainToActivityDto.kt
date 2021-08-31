package ru.gubatenko.data_impl.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.domain.model.Activity

class DomainToActivityDto : Mapper<Activity, ActivityDto>() {
    override fun map(data: Activity) = ActivityDto(
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}