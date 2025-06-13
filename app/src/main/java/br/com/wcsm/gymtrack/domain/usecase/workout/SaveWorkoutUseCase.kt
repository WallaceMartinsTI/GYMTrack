package br.com.wcsm.gymtrack.domain.usecase.workout

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Workout
import kotlinx.coroutines.flow.Flow

class SaveWorkoutUseCase(
    private val saveWorkout: suspend (
        userId: String,
        workout: Workout
    ) -> Flow<BaseResponse<Boolean>>
) {
    suspend operator fun invoke(
        userId: String,
        workout: Workout
    ): Flow<BaseResponse<Boolean>> {
        return saveWorkout(userId, workout)
    }
}