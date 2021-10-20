package ru.gubatenko.data_impl.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.data_impl.sqlite.IdeaStoredEntity
import ru.gubatenko.domain.model.Idea

class ActivityFromDomainToStoredRoom : Mapper<Idea, IdeaStored>() {
    override fun map(data: Idea) = IdeaStoredEntity(
        idea = data.activity,
    )
}