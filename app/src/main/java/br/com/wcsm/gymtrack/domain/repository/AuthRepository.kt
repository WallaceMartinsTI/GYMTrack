package br.com.wcsm.gymtrack.domain.repository

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getCurrentUser(): Flow<BaseResponse<String>>

    suspend fun signUp(
        userName: String,
        email: String,
        password: String
    ): Flow<BaseResponse<Boolean>>

    suspend fun signIn(email: String, password: String): Flow<BaseResponse<Boolean>>

    suspend fun signOut(): Flow<BaseResponse<Boolean>>
}