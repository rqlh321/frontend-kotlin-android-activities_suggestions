package com.example.lib.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.domain.model.Activity

class DomainToActivityDto : Mapper<Activity, IdeaDto>() {
    override fun map(data: Activity) = IdeaDto(
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}