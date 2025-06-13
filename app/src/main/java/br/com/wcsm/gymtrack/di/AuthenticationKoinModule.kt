package br.com.wcsm.gymtrack.di

import br.com.wcsm.gymtrack.domain.repository.AuthRepository
import br.com.wcsm.gymtrack.domain.usecase.authentication.GetCurrentUserUseCase
import br.com.wcsm.gymtrack.domain.usecase.authentication.SignInUseCase
import br.com.wcsm.gymtrack.domain.usecase.authentication.SignOutUseCase
import br.com.wcsm.gymtrack.domain.usecase.authentication.SignUpUseCase
import br.com.wcsm.gymtrack.presentation.pages.home.viewmodel.HomeViewModel
import br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel.SignInViewModel
import br.com.wcsm.gymtrack.presentation.pages.signup.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object AuthenticationKoinModule : KoinModule {
    override val module = module {
        single { SignUpUseCase(get<AuthRepository>()::signUp) }
        single { SignInUseCase(get<AuthRepository>()::signIn) }
        single { SignOutUseCase(get<AuthRepository>()::signOut) }

        single { GetCurrentUserUseCase(get<AuthRepository>()::getCurrentUser) }

        viewModelOf(::SignUpViewModel)
        viewModelOf(::SignInViewModel)
        viewModelOf(::HomeViewModel)
    }
}