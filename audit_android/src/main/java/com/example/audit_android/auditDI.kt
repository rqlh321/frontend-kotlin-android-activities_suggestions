package com.example.audit_android

import com.example.audit.Logger
import org.koin.dsl.module

fun auditModule() = module { single<Logger> { LoggerTimber() } }
