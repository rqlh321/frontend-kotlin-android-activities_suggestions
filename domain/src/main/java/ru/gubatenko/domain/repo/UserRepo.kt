package ru.gubatenko.domain.repo

import ru.gubatenko.domain.model.User

interface UserRepo {
    suspend fun create(query: CreateQuery)
    suspend fun read(query: ReadQuery): List<User>
    suspend fun update(query: UpdateQuery)
    suspend fun delete(query: DeleteQuery)

    sealed class CreateQuery

    sealed class ReadQuery {
        object GetSignedInUserQuery : UserRepo.ReadQuery()

    }

    sealed class UpdateQuery {
        data class SignInUserQuery(val credential: Any) : UserRepo.UpdateQuery()
        object SignOutUserQuery : UserRepo.UpdateQuery()
        object RefreshData : UserRepo.UpdateQuery()
    }

    sealed class DeleteQuery

}
