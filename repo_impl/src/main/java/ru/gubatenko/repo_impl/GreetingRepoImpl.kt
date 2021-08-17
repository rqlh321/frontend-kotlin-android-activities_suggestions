package ru.gubatenko.repo_impl

import ru.gubatenko.domain.model.Greeting
import ru.gubatenko.domain.repo.GreetingRepo
import java.util.UUID

class GreetingRepoImpl : GreetingRepo {
    override suspend fun create(value: Greeting) {
        TODO("Not yet implemented")
    }

    override suspend fun read() = Greeting(
        id = UUID.randomUUID().toString(),
        value = "Hello World!"
    )

    override suspend fun update(value: Greeting) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(value: Greeting) {
        TODO("Not yet implemented")
    }
}