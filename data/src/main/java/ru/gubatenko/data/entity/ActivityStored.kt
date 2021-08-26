package ru.gubatenko.data.entity

import ru.gubatenko.domain.model.Activity

class ActivityStored(
    val activity: String,
)

fun ActivityStored.toDomain() = Activity(
    activity = activity,
    type = null,
    accessibility = null,
)

fun Activity.toStored()= ActivityStored(
    activity = activity,
)