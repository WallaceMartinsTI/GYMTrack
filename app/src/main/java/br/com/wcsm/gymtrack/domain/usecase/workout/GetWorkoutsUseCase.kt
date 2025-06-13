package br.com.wcsm.gymtrack.domain.usecase.workout

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Workout
import kotlinx.coroutines.flow.Flow

class GetWorkoutsUseCase(
    private val getWorkouts: suspend (userId: String) -> Flow<BaseResponse<List<Workout>>>
) {
    suspend operator fun invoke(userId: String): Flow<BaseResponse<List<Workout>>> {
        return getWorkouts(userId)
    }
}