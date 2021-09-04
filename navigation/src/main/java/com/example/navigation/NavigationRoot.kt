package com.example.navigation

interface NavigationRoot {
    fun startAuthorizationFlow()
    fun navigationScopeId(scope: NavigationScope):Int
}