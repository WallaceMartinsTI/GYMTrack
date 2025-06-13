package br.com.wcsm.gymtrack.data.remote.repository

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Workout
import br.com.wcsm.gymtrack.domain.repository.WorkoutRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class WorkoutRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore
) : WorkoutRepository {
    override suspend fun getWorkouts(userId: String): Flow<BaseResponse<List<Workout>>> = flow {
        emit(BaseResponse.Loading)

        try {
            val snapshot = firebaseFirestore
                .collection("users")
                .document(userId)
                .collection("workouts")
                .get()
                .await()

            val workouts = snapshot.toObjects(Workout::class.java)
            emit(BaseResponse.Success(workouts))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro ao buscar treinos, tente mais tarde."))
        }
    }

    override suspend fun saveWorkout(
        userId: String,
        workout: Workout
    ): Flow<BaseResponse<Boolean>> = flow {
        emit(BaseResponse.Loading)

        try {
            val docRef = firebaseFirestore
                .collection("users")
                .document(userId)
                .collection("workouts")
                .document(workout.id)

            docRef.set(workout).await()
            emit(BaseResponse.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro ao criar treino, tente novamente mais tarde."))
        }
    }

    override suspend fun updateWorkout(
        userId: String,
        workout: Workout,
    ): Flow<BaseResponse<Boolean>> = flow {
        emit(BaseResponse.Loading)
        try {
            firebaseFirestore
                .collection("users")
                .document(userId)
                .collection("workouts")
                .document(workout.id)
                .set(workout)
                .await()

            emit(BaseResponse.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro ao atualizar treino."))
        }
    }

    override suspend fun deleteWorkout(
        userId: String,
        workoutId: String,
    ): Flow<BaseResponse<Boolean>> = flow {
        emit(BaseResponse.Loading)
        try {
            firebaseFirestore
                .collection("users")
                .document(userId)
                .collection("workouts")
                .document(workoutId)
                .delete()
                .await()

            emit(BaseResponse.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro ao excluir treino."))
        }
    }
}