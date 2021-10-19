package ru.gubatenko.data.entity

interface IdeaStored {
    val uid: Long
    val idea: String
    val isSynced: Boolean
}