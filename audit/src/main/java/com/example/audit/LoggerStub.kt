package com.example.audit

class LoggerStub : Logger {

    override fun d(throwable: Throwable) = Unit
    override fun d(message: String) = Unit
    override fun e(message: String) = Unit
    override fun e(throwable: Throwable, message: String) = Unit
    override fun i(message: String) = Unit
}