package com.example.navigation

interface NavigationRoot {
    fun startAuthorizationFlow()
    fun oferAuthorizationFlow()
    fun navigationScopeId(scope: NavigationScope):Int
}