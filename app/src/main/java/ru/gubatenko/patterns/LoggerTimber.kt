package ru.gubatenko.patterns

import com.example.audit.Logger
import timber.log.Timber

class LoggerTimber : Logger {
    override fun d(throwable: Throwable) = Timber.d(throwable)
    override fun d(message: String) = Timber.d(message)
    override fun e(message: String) = Timber.e(message)
    override fun e(throwable: Throwable, message: String) = Timber.e(throwable, message)
    override fun i(message: String) = Timber.i(message)
}