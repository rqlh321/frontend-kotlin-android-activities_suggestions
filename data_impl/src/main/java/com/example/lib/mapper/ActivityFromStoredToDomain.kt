package com.example.lib.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.domain.model.Idea

class ActivityFromStoredToDomain : Mapper<IdeaStored, Idea>() {
    override fun map(data: IdeaStored) = Idea(
        uid = data.uid,
        activity = data.idea,
        type = null,
        accessibility = null,
        isSynced = data.isSynced,
    )
}