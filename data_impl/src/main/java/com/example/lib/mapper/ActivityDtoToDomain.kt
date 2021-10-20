package com.example.lib.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.domain.model.Idea

class ActivityDtoToDomain : Mapper<IdeaDto, Idea>() {
    override fun map(data: IdeaDto) = Idea(
        uid = null,
        activity = data.activity,
        type = null,
        accessibility = null,
        isSynced = true,
    )
}