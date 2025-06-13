package br.com.wcsm.gymtrack.domain.usecase.authentication

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import kotlinx.coroutines.flow.Flow

class SignInUseCase(
    private val signIn: suspend (
        email: String,
        password: String
    ) -> Flow<BaseResponse<Boolean>>
) {
    suspend operator fun invoke(email: String, password: String): Flow<BaseResponse<Boolean>> {
        return signIn(email, password)
    }
}