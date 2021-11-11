package ru.gubatenko.domain.model

data class Idea(
    val uid: Long? = null,
    val activity: String,
    val type: String? = null,
    val accessibility: String? = null,
    val isSynced: Boolean? = null,
)