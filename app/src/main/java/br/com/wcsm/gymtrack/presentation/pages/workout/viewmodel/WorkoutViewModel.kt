package br.com.wcsm.gymtrack.presentation.pages.workout.viewmodel

import androidx.lifecycle.viewModelScope
import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.domain.usecase.workout.DeleteWorkoutUseCase
import br.com.wcsm.gymtrack.domain.usecase.workout.GetWorkoutsUseCase
import br.com.wcsm.gymtrack.domain.usecase.workout.SaveWorkoutUseCase
import br.com.wcsm.gymtrack.domain.usecase.workout.UpdateWorkoutUseCase
import br.com.wcsm.gymtrack.presentation.model.BaseFlowViewModel
import br.com.wcsm.gymtrack.presentation.model.CRUDAction
import br.com.wcsm.gymtrack.utils.UnitCallback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class WorkoutViewModel(
    private val getWorkoutsUseCase: GetWorkoutsUseCase,
    private val saveWorkoutUseCas: SaveWorkoutUseCase,
    private val updateWorkoutUseCase: UpdateWorkoutUseCase,
    private val deleteWorkoutUseCase: DeleteWorkoutUseCase
) : BaseFlowViewModel<WorkoutState, WorkoutUiState>(
    initialUiState = WorkoutUiState()
) {
    fun handleCurrentUser(currentUserId: String?) = _uiState.update { state -> state.copy(
        currentUserId = currentUserId
    ) }

    fun handleTitle(title: String) = _uiState.update { state -> state.copy(title = title) }

    fun handleDescription(description: String) = _uiState.update { state -> state.copy(
        description = description
    ) }

    fun resetErrorMessage() = _uiState.update { state -> state.copy(error = null) }

    private fun resetWorkoutParams() = _uiState.update { state -> state.copy(
        title = "",
        titleErrorMessage = "",
        description = "",
        descriptionErrorMessage = ""
    ) }

    fun setActionSuccessMessage(message: String) = _uiState.update { state -> state.copy(actionSuccessMessage = message) }

    private fun resetActionSuccessMessage() = _uiState.update { state -> state.copy(actionSuccessMessage = null) }

    fun showToastMessage(message: String) {
        emitState(WorkoutState.ShowToast(message))
        resetActionSuccessMessage()
    }

    fun onLeaveWorkoutFormPage() {
        resetWorkoutParams()
        resetErrorMessage()
    }

    private fun onLoadingResponse() = _uiState.update { state -> state.copy(isLoading = true) }

    private fun onErrorResponse(errorMessage: String) = _uiState.update { state ->
        state.copy(
            isLoading = false,
            error = errorMessage
        )
    }

    private fun onSuccessResponse(
        crudAction: CRUDAction? = null,
        onSuccessCallback: UnitCallback
    ) {
        onSuccessCallback()

        if(crudAction != null) {
            resetWorkoutParams()
            emitState(WorkoutState.WorkoutActionSuccess(crudAction))

            setActionSuccessMessage("")
        }

        _uiState.update { state ->
            state.copy(
                isLoading = false
            )
        }
    }

    fun getWorkouts() {
        val currentUserId = uiState.value.currentUserId
        currentUserId?.let {
            viewModelScope.launch(Dispatchers.IO) {
                getWorkoutsUseCase(userId = currentUserId).collect { result ->
                    when(result) {
                        is BaseResponse.Loading -> onLoadingResponse()
                        is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                        is BaseResponse.Success -> onSuccessResponse {
                            _uiState.update { state -> state.copy(
                                workouts = result.data
                            ) }
                        }
                    }
                }
            }
        }
    }

    fun setSelectedWorkout(workout: Workout) {
        _uiState.update { state -> state.copy(
            selectedWorkout = workout,
            title = workout.title,
            description = workout.description
        ) }
    }

    fun saveWorkout() {
        val currentUserId = uiState.value.currentUserId
        currentUserId?.let {

            val workoutTitle = uiState.value.title
            val workoutDescription = uiState.value.description

            if(isWorkoutValid()) {
                val workoutToSave = Workout(
                    id = UUID.randomUUID().toString(),
                    title = workoutTitle,
                    description = workoutDescription,
                    exercisesCount = 0,
                    imageUrl = ""
                )

                viewModelScope.launch(Dispatchers.IO) {
                    saveWorkoutUseCas(
                        userId = currentUserId,
                        workout = workoutToSave
                    ).collect { result ->
                        when(result) {
                            is BaseResponse.Loading -> onLoadingResponse()
                            is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                            is BaseResponse.Success -> onSuccessResponse(
                                crudAction = CRUDAction.SAVE
                            ) {}
                        }
                    }
                }
            }
        }
    }
    
    fun updateWorkout() {
        val currentUserId = uiState.value.currentUserId
        currentUserId?.let {
            uiState.value.selectedWorkout?.let { workout ->
                viewModelScope.launch(Dispatchers.IO) {
                    updateWorkoutUseCase(
                        userId = currentUserId,
                        workout = workout
                    ).collect { result ->
                        when(result) {
                            is BaseResponse.Loading -> onLoadingResponse()
                            is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                            is BaseResponse.Success -> onSuccessResponse(
                                crudAction = CRUDAction.UPDATE
                            ) {}
                        }
                    }
                }
            }
        }
    }
    
    fun deleteWorkout() {
        val currentUserId = uiState.value.currentUserId
        currentUserId?.let {
            uiState.value.selectedWorkout?.let { workout ->
                viewModelScope.launch(Dispatchers.IO) {
                    deleteWorkoutUseCase(
                        userId = currentUserId,
                        workoutId = workout.id
                    ).collect { result ->
                        when(result) {
                            is BaseResponse.Loading -> onLoadingResponse()
                            is BaseResponse.Error -> onErrorResponse(result.errorMessage)
                            is BaseResponse.Success -> onSuccessResponse(
                                crudAction = CRUDAction.DELETE
                            ) {}
                        }
                    }
                }
            }
        }
    }

    private fun isWorkoutValid(): Boolean {
        val isTitleValid = validateTitle(uiState.value.title)
        val isDescriptionValid = validateDescription(uiState.value.description)

        _uiState.update { state -> state.copy(
            titleErrorMessage = isTitleValid.second,
            descriptionErrorMessage = isDescriptionValid.second
        ) }

        return isTitleValid.first && isDescriptionValid.first
    }

    private fun validateTitle(title: String): Pair<Boolean, String> {
        return when {
            title.isBlank() -> false to "Você deve informar o título do treino."
            title.length > TITLE_MAX_LENGTH -> false to "Título muito grande (${title.length}/$TITLE_MAX_LENGTH)."
            else -> true to ""
        }
    }

    private fun validateDescription(description: String): Pair<Boolean, String> {
        return when {
            description.isBlank() -> false to "Você deve informar uma descrição do treino."
            description.length > DESCRIPTION_MAX_LENGTH -> false to "Descrição muito grande (${description.length}/$DESCRIPTION_MAX_LENGTH)."
            else -> true to ""
        }
    }

    companion object {
        const val TITLE_MAX_LENGTH = 25
        const val DESCRIPTION_MAX_LENGTH = 100
    }
}