package br.com.wcsm.gymtrack.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Home : Route

    @Serializable
    data class Workout(val workout: String)

    @Serializable
    data class WorkoutForm(val workout: String)
}

