package br.com.wcsm.gymtrack.presentation.pages.workout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.wcsm.gymtrack.domain.model.Exercise
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.atomic.templates.WorkoutTemplate
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutState
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WorkoutPage(
    workout: Workout,
    workoutViewModel: WorkoutViewModel,
    onEditWorkout: (Workout) -> Unit,
    onBackClick: UnitCallback
) {
    val exercise = Exercise(
        id = "1",
        workoutId = "1",
        title = "Treino de Pernas Básico",
        notes = "Agachamento 4 séries de 30 repetições",
        imageUrl = ""
    )

    val workoutExercises = listOf(
        exercise,
        exercise.copy(id = "2", "1", "Treino 2", "Descrição Treino 2"),
        exercise.copy(id = "3", "1", "Treino 3", "Descrição Treino 3")
    )

    val uiState by workoutViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(workoutViewModel) {
        workoutViewModel.state.collectLatest { state ->
            when(state) {
                is WorkoutState.ShowToast -> {}
                is WorkoutState.WorkoutActionSuccess -> {}
            }
        }
    }

    WorkoutTemplate(
        isLoading = uiState.isLoading,
        workout = workout,
        workoutExercises = workoutExercises,
        onEditWorkout = onEditWorkout,
        onBackClick = onBackClick
    )
}