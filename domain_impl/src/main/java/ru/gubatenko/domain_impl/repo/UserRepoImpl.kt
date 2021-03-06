package ru.gubatenko.domain_impl.repo

import ru.gubatenko.data.pref.CustomDataStore
import ru.gubatenko.data.service.UserService
import ru.gubatenko.domain.model.User
import ru.gubatenko.domain.repo.UserRepo

class UserRepoImpl(
    private val userService: UserService,
    private val customDataStore: CustomDataStore,
) : UserRepo {
    override suspend fun create(query: UserRepo.CreateQuery) {
        TODO("Not yet implemented")
    }

    override suspend fun read(query: UserRepo.ReadQuery) = when (query) {
        is UserRepo.ReadQuery.GetSignedInUserQuery -> {
            userService.user()?.let {
                User(
                    uid = it.uid,
                    name = it.name,
                    avatar = it.avatar,
                    email = it.email,
                )
            }?.let(::listOf) ?: emptyList()
        }
    }

    override suspend fun update(query: UserRepo.UpdateQuery) = when (query) {
        is UserRepo.UpdateQuery.SignOutUserQuery -> userService.signOut()
        is UserRepo.UpdateQuery.SignInUserQuery -> userService.signIn(query.credential)
        is UserRepo.UpdateQuery.RefreshData -> {

        }
    }

    override suspend fun delete(query: UserRepo.DeleteQuery) {
        TODO("Not yet implemented")
    }
}
