package com.example.navigation

interface NavigationRoot {
    fun setupNotAuthorized()
    fun startAuthorizationFlow()
    fun navigationScopeId(scope: NavigationScope):Int
}