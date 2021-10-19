package com.example.lib.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.domain.model.Activity

class ActivityDtoToDomain : Mapper<IdeaDto, Activity>() {
    override fun map(data: IdeaDto) = Activity(
        uid = null,
        activity = data.activity,
        type = null,
        accessibility = null,
        isSynced = true,
    )
}