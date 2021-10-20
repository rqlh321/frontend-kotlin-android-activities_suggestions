package ru.gubatenko.domain.model

data class Idea(
    val uid: Long?,
    val activity: String,
    val type: String?,
    val accessibility: String?,
    val isSynced: Boolean?,
)