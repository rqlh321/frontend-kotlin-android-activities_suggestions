package com.example.audit

interface Logger {
    fun d(throwable: Throwable)
    fun d(message: String)
    fun e(message: String)
    fun e(throwable: Throwable, message: String)
    fun i(message: String)
}
