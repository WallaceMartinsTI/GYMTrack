package br.com.wcsm.gymtrack.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface AuthRoute {
    @Serializable
    data object Welcome : AuthRoute

    @Serializable
    data object SignIn : AuthRoute

    @Serializable
    data object SignUpMessage : AuthRoute
    @Serializable
    data object SignUp : AuthRoute

    @Serializable
    data object AppNavigation : AuthRoute
}