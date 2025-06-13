package br.com.wcsm.gymtrack.data.remote.repository

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.User
import br.com.wcsm.gymtrack.domain.repository.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepositoryImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {
    override suspend fun getCurrentUser(): Flow<BaseResponse<String>> = flow {
        emit(BaseResponse.Loading)

        try {
            val currentUser = firebaseAuth.currentUser
            if(currentUser != null) {
                emit(BaseResponse.Success(currentUser.uid))
            } else {
                emit(BaseResponse.Error("Não existe um usuário logado."))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro ao buscar usuário logado, tente novamente mais tarde."))
        }
    }

    override suspend fun signUp(
        userName: String,
        email: String,
        password: String,
    ): Flow<BaseResponse<Boolean>> = flow {
        emit(BaseResponse.Loading)

        try {
            val authResult = suspendCancellableCoroutine<AuthResult> { cont ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            cont.resume(task.result)
                        } else {
                            cont.resumeWithException(task.exception ?: Exception("Erro ao criar conta."))
                        }
                    }
            }

            val userId = authResult.user?.uid ?: throw Exception("ID do usuário não encontrado.")

            val user = User(
                id = userId,
                name = userName,
                email = email
            )

            suspendCancellableCoroutine { cont ->
                firebaseFirestore
                    .collection("users")
                    .document(userId)
                    .set(user)
                    .addOnSuccessListener { cont.resume(Unit) }
                    .addOnFailureListener { exception ->
                        cont.resumeWithException(exception)
                    }
            }

            emit(BaseResponse.Success(true))
        } catch (e: FirebaseAuthWeakPasswordException) {
            e.printStackTrace()
            emit(BaseResponse.Error("Senha fraca, use letras, números e caracteres especiais."))
        } catch (e: FirebaseAuthUserCollisionException) {
            e.printStackTrace()
            emit(BaseResponse.Error("E-mail já cadastrado."))
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            e.printStackTrace()
            emit(BaseResponse.Error("Credenciais inválidas."))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro desconhecido ao criar conta."))
        }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): Flow<BaseResponse<Boolean>> = flow {
        emit(BaseResponse.Loading)

        val result = suspendCancellableCoroutine<Result<Boolean>> { cont ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        cont.resume(Result.success(true))
                    } else {
                        val message = when (task.exception) {
                            is FirebaseAuthInvalidCredentialsException -> "Credenciais incorretas."
                            else -> "Erro ao fazer login."
                        }
                        cont.resume(Result.failure(Exception(message)))
                    }
                }
        }

        result.fold(
            onSuccess = { emit(BaseResponse.Success(true)) },
            onFailure = { emit(BaseResponse.Error(it.message ?: "Erro desconhecido")) }
        )
    }

    override suspend fun signOut(): Flow<BaseResponse<Boolean>> = flow {
        emit(BaseResponse.Loading)

        try {
            firebaseAuth.signOut()
            emit(BaseResponse.Success(true))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(BaseResponse.Error("Erro desconhecido ao deslogar usuário."))
        }
    }
}