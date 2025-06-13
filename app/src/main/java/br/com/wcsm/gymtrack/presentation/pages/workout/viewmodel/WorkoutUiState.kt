package br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel

import br.com.wcsm.gymtrack.domain.model.Workout

data class WorkoutUiState(
    val currentUserId: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val actionSuccessMessage: String? = null,
    val selectedWorkout: Workout? = null,
    val workouts: List<Workout> = emptyList(),
    val title: String = "",
    val titleErrorMessage: String = "",
    val description: String = "",
    val descriptionErrorMessage: String = "",
)
