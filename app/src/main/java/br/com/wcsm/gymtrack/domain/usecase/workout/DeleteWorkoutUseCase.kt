package br.com.wcsm.gymtrack.domain.usecase.workout

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import kotlinx.coroutines.flow.Flow

class DeleteWorkoutUseCase(
    private val deleteWorkout: suspend (
        userId: String,
        workoutId: String
    ) -> Flow<BaseResponse<Boolean>>
) {
    suspend operator fun invoke(
        userId: String,
        workoutId: String
    ): Flow<BaseResponse<Boolean>> {
        return deleteWorkout(userId, workoutId)
    }
}