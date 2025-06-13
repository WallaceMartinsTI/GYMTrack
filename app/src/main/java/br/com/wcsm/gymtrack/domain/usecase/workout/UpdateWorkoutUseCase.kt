package br.com.wcsm.gymtrack.domain.usecase.workout

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Workout
import kotlinx.coroutines.flow.Flow

class UpdateWorkoutUseCase(
    private val updateWorkout: suspend (
        userId: String,
        workout: Workout
    ) -> Flow<BaseResponse<Boolean>>
) {
    suspend operator fun invoke(
        userId: String,
        workout: Workout
    ): Flow<BaseResponse<Boolean>> {
        return updateWorkout(userId, workout)
    }
}