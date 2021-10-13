package com.example.navigation

interface NavigationMain {
    /**
     * Run Authorization Activity
     * */
    fun startAuthorizationFlow()
    /**
     * Show dialog that offers authorization
     * */
    fun oferAuthorizationFlow()

    fun frameGraphId(): Int
}
