package br.com.wcsm.gymtrack.di

import br.com.wcsm.gymtrack.domain.repository.AuthRepository
import br.com.wcsm.gymtrack.domain.usecase.signin.SignInUseCase
import br.com.wcsm.gymtrack.presentation.pages.signin.viewmodel.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object SignInKoinModule : KoinModule {
    override val module = module {
        single { SignInUseCase(get<AuthRepository>()::signIn) }

        viewModelOf(::SignInViewModel)
    }
}