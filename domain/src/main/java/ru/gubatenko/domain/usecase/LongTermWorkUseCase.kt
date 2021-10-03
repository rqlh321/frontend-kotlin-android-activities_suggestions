package ru.gubatenko.domain.usecase

interface LongTermWorkUseCase {
    fun execute(query: Query)

    sealed class Query {
        object StartUpload : Query()
        object StartDownload : Query()
        object StopLoad : Query()
    }
}