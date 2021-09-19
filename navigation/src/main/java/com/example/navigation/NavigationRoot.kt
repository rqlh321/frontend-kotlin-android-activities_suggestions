package com.example.navigation

interface NavigationRoot {
    fun changeUserStateToUnAuthorized()
    fun startAuthorizationFlow()
    fun navigationScopeId(scope: NavigationScope):Int
}