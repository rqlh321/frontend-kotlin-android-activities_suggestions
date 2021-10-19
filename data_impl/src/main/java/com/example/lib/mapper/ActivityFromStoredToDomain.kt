package com.example.lib.mapper

import ru.gubatenko.data.Mapper
import ru.gubatenko.data.entity.IdeaStored
import ru.gubatenko.domain.model.Activity

class ActivityFromStoredToDomain : Mapper<IdeaStored, Activity>() {
    override fun map(data: IdeaStored) = Activity(
        uid = data.uid,
        activity = data.idea,
        type = null,
        accessibility = null,
        isSynced = data.isSynced,
    )
}