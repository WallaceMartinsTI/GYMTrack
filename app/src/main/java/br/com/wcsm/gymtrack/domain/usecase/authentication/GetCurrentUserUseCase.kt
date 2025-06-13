package br.com.wcsm.gymtrack.domain.usecase.authentication

import br.com.wcsm.gymtrack.domain.model.BaseResponse
import kotlinx.coroutines.flow.Flow

class GetCurrentUserUseCase(
    private val getCurrentUser: suspend () -> Flow<BaseResponse<String>>
) {
    suspend operator fun invoke(): Flow<BaseResponse<String>> {
        return getCurrentUser()
    }
}