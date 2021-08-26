package ru.gubatenko.data.dto

import ru.gubatenko.domain.model.Activity

data class ActivityDto(
    val activity: String,
    val type: String,
    val accessibility: String,
)

fun ActivityDto.toDomain() = Activity(
    activity = activity,
    type = type,
    accessibility = accessibility,
)