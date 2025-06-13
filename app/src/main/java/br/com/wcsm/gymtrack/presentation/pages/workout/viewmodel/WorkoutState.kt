package br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel

import br.com.wcsm.gymtrack.presentation.model.CRUDAction

sealed class WorkoutState {
    // Mudar para snackbar se der tempo
    data class ShowToast(val message: String) : WorkoutState()
    data class WorkoutActionSuccess(val action: CRUDAction) : WorkoutState()
}