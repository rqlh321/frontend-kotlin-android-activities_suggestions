package ru.gubatenko.domain.usecase

interface SetPrefUseCase {
    
    suspend fun execute(query: Query)

    sealed class Query {
        data class SwitchPrefQuery(
            val id: String,
            val value: Boolean
        ) : Query()
    }
}
