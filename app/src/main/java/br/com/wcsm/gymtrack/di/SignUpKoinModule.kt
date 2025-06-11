package br.com.wcsm.gymtrack.di

import br.com.wcsm.gymtrack.domain.repository.AuthRepository
import br.com.wcsm.gymtrack.domain.usecase.signin.SignUpUseCase
import br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object SignUpKoinModule : KoinModule {
    override val module = module {
        single { SignUpUseCase(get<AuthRepository>()::signUp) }

        viewModelOf(::SignUpViewModel)
    }
}