package ru.gubatenko.use_case_impl

import ru.gubatenko.domain.model.Greeting
import ru.gubatenko.domain.repo.GreetingRepo
import ru.gubatenko.domain.usecase.GreetingUseCase

class GreetingUseCaseImpl(
    private val repo: GreetingRepo
) : GreetingUseCase {
    override suspend fun multipleGreeting(): List<Greeting> {
        val greeting = repo.read()
        val result = listOf(greeting.copy(), greeting.copy(), greeting.copy(), greeting.copy())
        return result
    }
}