package ru.gubatenko.data_impl.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.domain.model.Activity

class ActivityDtoToDomain : Mapper<ActivityDto, Activity>() {
    override fun map(data: ActivityDto) = Activity(
        uid = null,
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}