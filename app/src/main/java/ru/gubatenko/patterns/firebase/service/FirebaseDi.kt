package ru.gubatenko.patterns.firebase.service

import org.koin.dsl.module
import ru.gubatenko.data.service.UserService

val serviceFirebaseImplModuleDI = module {
    single<UserService> { UserServiceImpl() }
}