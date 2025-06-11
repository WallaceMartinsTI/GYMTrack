package br.com.wcsm.gymtrack.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Welcome : Route

    @Serializable
    data object SignIn : Route

    @Serializable
    data object SignUpMessage : Route
    @Serializable
    data object SignUp : Route

    @Serializable

    data object Home : Route
}