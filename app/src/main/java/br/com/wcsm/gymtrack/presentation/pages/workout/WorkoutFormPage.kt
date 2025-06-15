package br.com.wcsm.gymtrack.presentation.pages.workout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.wcsm.gymtrack.R
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.presentation.atomic.templates.WorkoutFormTemplate
import br.com.wcsm.gymtrack.presentation.model.CRUDAction
import br.com.wcsm.gymtrack.presentation.model.TextFieldParams
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutState
import br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel.WorkoutViewModel
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.flow.collectLatest

@Composable
fun WorkoutFormPage(
    workout: Workout?,
    workoutViewModel: WorkoutViewModel,
    onNavigateUp: UnitCallback
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val uiState by workoutViewModel.uiState.collectAsStateWithLifecycle()

    val isWorkoutToUpdate = workout != null

    DisposableEffect(Unit) {
        onDispose {
            workoutViewModel.onLeaveWorkoutFormPage()
        }
    }

    LaunchedEffect(workoutViewModel) {
        workoutViewModel.state.collectLatest { state ->
            if(state is WorkoutState.WorkoutActionSuccess) {
                keyboardController?.hide()

                val message = when (state.action) {
                    CRUDAction.SAVE -> "Treino salvo com sucesso!"
                    CRUDAction.UPDATE -> "Treino atualizado com sucesso!"
                    CRUDAction.DELETE -> "Treino excluído com sucesso!"
                }

                workoutViewModel.setActionSuccessMessage(message)

                workoutViewModel.onLeaveWorkoutFormPage()

                onNavigateUp()
            }
        }
    }

    WorkoutFormTemplate(
        isWorkoutToUpdate = isWorkoutToUpdate,
        pageTitle = if(isWorkoutToUpdate) stringResource(R.string.workout_form_update_workout_page_title)
        else stringResource(R.string.workout_form_add_workout_page_title),
        pageMessage = if(isWorkoutToUpdate) stringResource(R.string.workout_form_update_workout_page_message)
        else stringResource(R.string.workout_form_add_workout_page_message),
        titleParams = TextFieldParams(
            value = uiState.title,
            onValueChange = workoutViewModel::handleTitle,
            label = stringResource(R.string.workout_form_title_label),
            placeholder = stringResource(R.string.workout_form_title_placeholder),
            isError = uiState.titleErrorMessage.isNotBlank(),
            supportingText = uiState.titleErrorMessage,
            onClearField = { workoutViewModel.handleTitle("") }
        ),
        descriptionParams = TextFieldParams(
            value = uiState.description,
            onValueChange = workoutViewModel::handleDescription,
            label = stringResource(R.string.workout_form_description_label),
            placeholder = stringResource(R.string.workout_form_description_placeholder),
            isError = uiState.descriptionErrorMessage.isNotBlank(),
            supportingText = uiState.descriptionErrorMessage,
            onClearField = { workoutViewModel.handleDescription("") }
        ),
        errorMessage = uiState.error,
        buttonText = if(isWorkoutToUpdate) stringResource(R.string.workout_form_update_workout_text)
        else stringResource(R.string.workout_form_save_workout_text),
        onSubmit = if(isWorkoutToUpdate) workoutViewModel::updateWorkout
        else workoutViewModel::saveWorkout,
        onDelete = workoutViewModel::deleteWorkout,
        onBack = onNavigateUp
    )
}