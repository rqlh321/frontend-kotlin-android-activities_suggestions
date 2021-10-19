package ru.gubatenko.data_impl.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.data_impl.sqlite.IdeaStoredEntity
import ru.gubatenko.domain.model.Activity

class ActivityFromDomainToStoredRoom : Mapper<Activity, IdeaStored>() {
    override fun map(data: Activity) = IdeaStoredEntity(
        idea = data.activity,
    )
}