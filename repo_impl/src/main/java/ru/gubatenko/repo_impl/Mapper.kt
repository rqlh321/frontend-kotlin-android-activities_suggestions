package ru.gubatenko.repo_impl

interface Mapper<From, To> {
    fun convert(data: From): To
}