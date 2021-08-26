package ru.gubatenko.data

abstract class Mapper<Input, Output> {

    abstract fun map(data: Input): Output
}