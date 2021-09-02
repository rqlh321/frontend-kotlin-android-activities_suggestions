package ru.gubatenko.domain_impl.repo

import ru.gubatenko.data.service.UserService
import ru.gubatenko.domain.model.User
import ru.gubatenko.domain.repo.UserRepo

class UserRepoImpl(
    private val service: UserService
) : UserRepo {
    override suspend fun create(query: UserRepo.CreateQuery) {
        TODO("Not yet implemented")
    }

    override suspend fun read(query: UserRepo.ReadQuery) = when (query) {
        is UserRepo.ReadQuery.GetSignedInUserQuery -> {
            service.user()?.let { User(uid = it.uid) }?.let(::listOf) ?: emptyList()
        }
    }

    override suspend fun update(query: UserRepo.UpdateQuery) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(query: UserRepo.DeleteQuery) {
        TODO("Not yet implemented")
    }
}