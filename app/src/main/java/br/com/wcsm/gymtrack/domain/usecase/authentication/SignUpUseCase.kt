package br.com.wcsm.gymtrack.domain.usecase.authentication

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import kotlinx.coroutines.flow.Flow

class SignUpUseCase(
    private val signUp: suspend (
        userName: String,
        email: String,
        password: String
    ) -> Flow<BaseResponse<Boolean>>
) {
    suspend operator fun invoke(
        userName: String,
        email: String,
        password: String
    ): Flow<BaseResponse<Boolean>> {
        return signUp(userName, email, password)
    }
}