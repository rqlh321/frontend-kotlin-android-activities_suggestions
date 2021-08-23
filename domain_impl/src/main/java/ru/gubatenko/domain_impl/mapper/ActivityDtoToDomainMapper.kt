package ru.gubatenko.domain_impl.mapper

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain_impl.data.dto.ActivityDto

class ActivityDtoToDomainMapper : Mapper<ActivityDto, Activity> {
    override fun convert(data: ActivityDto) = Activity(
        activity = data.activity,
        type = data.type,
        accessibility = data.accessibility,
    )
}