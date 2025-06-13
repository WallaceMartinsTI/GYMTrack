package br.com.wcsm.gymtrack.domain.usecase.authentication

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import kotlinx.coroutines.flow.Flow

class SignOutUseCase(
    private val signOut: suspend () -> Flow<BaseResponse<Boolean>>
) {
    suspend operator fun invoke(): Flow<BaseResponse<Boolean>> {
        return signOut()
    }
}