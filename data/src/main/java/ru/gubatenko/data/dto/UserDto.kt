package ru.gubatenko.data.dto

data class UserDto(
    val uid: String,
    val name: String,
    val avatar: String?,
    val email: String?,
)