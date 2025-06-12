package br.com.wcsm.gymtrack.domain.repository

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Workout
import kotlinx.coroutines.flow.Flow

interface WorkoutRepository {
    suspend fun getWorkouts(userId: String): Flow<BaseResponse<List<Workout>>>
    suspend fun addWorkout(userId: String, workout: Workout): Flow<BaseResponse<Boolean>>
    suspend fun updateWorkout(userId: String, workout: Workout): Flow<BaseResponse<Boolean>>
    suspend fun deleteWorkout(userId: String, workoutId: String): Flow<BaseResponse<Boolean>>
}