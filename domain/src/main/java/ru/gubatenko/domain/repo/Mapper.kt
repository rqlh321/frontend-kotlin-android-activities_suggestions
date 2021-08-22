package ru.gubatenko.domain.repo

interface Mapper<From, To> {
    fun convert(data: From): To
}