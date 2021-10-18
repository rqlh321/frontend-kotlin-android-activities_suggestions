package ru.gubatenko.domain.navigation

interface NavigationMain {
    /**
     * Run Authorization Activity
     * */
    fun startAuthorizationFlow()
    /**
     * Show dialog that offers authorization
     * */
    fun oferAuthorizationFlow()

    fun restartApp()

    fun frameGraphId(): Int
}
