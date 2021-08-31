package ru.gubatenko.data.entity

interface ActivityStored {
    val uid: Long
    val activity: String
    val isSynced: Boolean
}