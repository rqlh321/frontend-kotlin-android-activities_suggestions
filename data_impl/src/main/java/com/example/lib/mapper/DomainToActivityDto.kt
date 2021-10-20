package com.example.lib.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.domain.model.Idea

class DomainToActivityDto : Mapper<Idea, IdeaDto>() {
    override fun map(data: Idea) = IdeaDto(
        activity = data.activity,
        type = null,
        accessibility = null,
    )
}